package bp.tools;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger(HttpConnectionManager.class);

    private static final PoolingHttpClientConnectionManager connectionManager;
    private static final CloseableHttpClient httpClient;
    private static final IdleConnectionMonitorThread monitorThread;
    static {
        try {
            logger.warn("HttpConnectionManager 配置为忽略 SSL 证书验证和主机名验证！请务必保证使用环境安全");
            TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
                        }
                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
                            if (logger.isTraceEnabled()) {
                                logger.trace("信任服务器证书 (authType={}): {}", authType, (chain != null && chain.length > 0) ? chain[0].getSubjectDN() : "null");
                            }
                        }
                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS"); // 或者 "SSL", 推荐使用 "TLS"
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    NoopHostnameVerifier.INSTANCE
            );
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("https", sslSocketFactory)
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .build();
            connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connectionManager.setMaxTotal(200);
            connectionManager.setDefaultMaxPerRoute(20);

            SocketConfig socketConfig = SocketConfig.custom()
                    .setSoTimeout(30000)
                    .build();
            connectionManager.setDefaultSocketConfig(socketConfig);
            connectionManager.setValidateAfterInactivity(10000);

            RequestConfig requestConfig = RequestConfig.custom()
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(30000)
                    .setConnectionRequestTimeout(5000)
                    .build();
            httpClient = HttpClients.custom()
                    .setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig)
                    .setConnectionManagerShared(true)
                    .build();
            monitorThread = new IdleConnectionMonitorThread(connectionManager);
            monitorThread.setDaemon(true);
            monitorThread.start();
            logger.info("HttpConnectionManager 初始化完成 (SSL 验证已禁用!)");
        } catch (NoSuchAlgorithmException | KeyManagementException e) { // 添加 KeyManagementException 到 catch
            logger.error("初始化 HttpConnectionManager 失败 (无法配置 SSL 绕过): {}", e.getMessage(), e);
            throw new RuntimeException("无法初始化 HttpConnectionManager", e);
        }
    }
    public static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            logger.error("HttpConnectionManager 未成功初始化，HttpClient 实例为 null!");
            throw new IllegalStateException("HttpConnectionManager has not been initialized properly.");
        }
        return httpClient;
    }

    public static void shutdown() {
        logger.info("正在关闭 HttpConnectionManager...");
        if (monitorThread != null) {
            monitorThread.shutdown();
            try {
                monitorThread.join(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.warn("等待闲置连接监控线程关闭时被中断", e);
            }
        }
        try {
            if (httpClient != null) {
                httpClient.close();
                logger.debug("HttpClient 已关闭");
            }
        } catch (IOException e) {
            logger.error("关闭 HttpClient 时出错: {}", e.getMessage(), e);
        }
        try {
            if (connectionManager != null) {
                connectionManager.close();
                logger.debug("PoolingHttpClientConnectionManager 已关闭");
            }
        } catch (Exception e) {
            logger.error("关闭 PoolingHttpClientConnectionManager 时出错: {}", e.getMessage(), e);
        }
        logger.info("HttpConnectionManager 关闭完成");
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("JVM 关闭钩子触发，正在关闭 HttpConnectionManager...");
            HttpConnectionManager.shutdown();
        }, "http-connection-manager-shutdown-hook"));
    }
    private static class IdleConnectionMonitorThread extends Thread {
        private final PoolingHttpClientConnectionManager connMgr;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(PoolingHttpClientConnectionManager connMgr) {
            super("idle-connection-monitor");
            this.connMgr = connMgr;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        if (shutdown) break;
                        try {
                            connMgr.closeExpiredConnections();
                            connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
                        } catch (Exception e) {
                            logger.error("闲置连接监控线程运行时出错: {}", e.getMessage(), e);
                        }
                    }
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }
}