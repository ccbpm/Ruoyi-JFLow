package bp.wf.minio;

import bp.da.DataType;
import bp.da.Log;
import bp.difference.SystemConfig;
import io.minio.*;
import okhttp3.OkHttpClient;
import org.apache.http.util.TextUtils;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class MinioUtil {
    private static MinioUtil instance;
    private MinioClient minioClient;
    private String minioHost;
    private String minioAccessKey;
    private String minioSecretKey;
    private String minioBucket;
    private Boolean isEnabledSSL;

    private MinioUtil() {
        try {
            // 检查配置
            checkConfigs();
            // 实例化minio客户端
            MinioClient.Builder builder = MinioClient.builder()
                    .endpoint(getMinioHost()) // Minio 服务器地址
                    .credentials(getMinioAccessKey(), getMinioSecretKey()); // 访问密钥和秘密密钥

            // 如果启用了 SSL，则设置自定义的 HttpClient
            if (isEnabledSSL) {
                builder.httpClient(getUnsafeOkHttpClient());
            }

            minioClient = builder.build();
            createBucketIfNotExists(getMinioBucket());
            Log.DebugWriteInfo("√ minio客户端实例化成功.");
        } catch (Exception e) {
            Log.DebugWriteError(e);
            throw new RuntimeException("创建MinIO实例时报错" + e);
        }
    }

    public static MinioUtil getInstance() {
        if (instance == null) {
            synchronized (MinioUtil.class) {
                if (instance == null) {
                    instance = new MinioUtil();
                }
            }
        }
        return instance;
    }

    public Boolean getEnabledSSL() {
        return isEnabledSSL;
    }

    public void setEnabledSSL(Boolean enabledSSL) {
        isEnabledSSL = enabledSSL;
    }

    public String getMinioHost() {
        return minioHost;
    }

    public void setMinioHost(String minioHost) {
        this.minioHost = minioHost;
    }

    public String getMinioAccessKey() {
        return minioAccessKey;
    }

    public void setMinioAccessKey(String minioAccessKey) {
        this.minioAccessKey = minioAccessKey;
    }

    public String getMinioSecretKey() {
        return minioSecretKey;
    }

    public void setMinioSecretKey(String minioSecretKey) {
        this.minioSecretKey = minioSecretKey;
    }

    public String getMinioBucket() {
        return minioBucket;
    }

    public void setMinioBucket(String minioBucket) {
        this.minioBucket = minioBucket;
    }

    private void checkConfigs() {
        String minioHost = SystemConfig.getMinioHost();
        if (DataType.IsNullOrEmpty(minioHost)) {
            throw new RuntimeException("MinioHost 未设置，请您在jflow.properties中配置");
        }
        setMinioHost(minioHost);
        String minioAccessKey = SystemConfig.getMinioAccessKey();
        if (DataType.IsNullOrEmpty(minioAccessKey)) {
            throw new RuntimeException("MinioAccessKey 未设置，请您在jflow.properties中配置");
        }
        setMinioAccessKey(minioAccessKey);
        String minioSecretKey = SystemConfig.getMinioSecretKey();
        if (DataType.IsNullOrEmpty(minioSecretKey)) {
            throw new RuntimeException("MinioSecretKey 未设置，请您在jflow.properties中配置");
        }
        setMinioSecretKey(minioSecretKey);
        String minioBucket = SystemConfig.getMinioBucket();
        if (DataType.IsNullOrEmpty(minioBucket)) {
            throw new RuntimeException("MinioBucket 未设置，请您在jflow.properties中配置");
        }
        setMinioBucket(minioBucket);
        Boolean minioEnabledSSL = SystemConfig.getMinioEnabledSSL(); // 非必须项，默认false，不启用Https
        setEnabledSSL(minioEnabledSSL);
    }

    public void createBucketIfNotExists(String bucketName) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                Log.DebugWriteInfo("Bucket [" + bucketName + "] 创建成功.");
            } else {
                Log.DebugWriteInfo("√ Bucket [" + bucketName + "] 已存在.");
            }
        } catch (Exception e) {
            Log.DebugWriteError(e);
            throw new RuntimeException("MinIO检查Bucket时报错 " + e);
        }
    }

    public void uploadObject(String objectName, byte[] data, String contentType) {
        createBucketIfNotExists(getMinioBucket());
        try (InputStream inputStream = new ByteArrayInputStream(data)) {
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(getMinioBucket())
                            .object(objectName)
                            .contentType(contentType)
                            .stream(inputStream, data.length, -1)
                            .build()
            );
            Log.DebugWriteInfo("√ 对象上传成功.");
        } catch (Exception e) {
            Log.DebugWriteError(e);
            throw new RuntimeException("MinIO上传文件时报错" + e);
        }
    }

    public void downloadObject(String objectName, String tempFile) {
        createBucketIfNotExists(getMinioBucket());
        try (InputStream is = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(getMinioBucket())
                        .object(objectName)
                        .build());
             OutputStream outStream = Files.newOutputStream(Paths.get(tempFile))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            Log.DebugWriteInfo("√ 对象下载成功.");
        } catch (Exception e) {
            Log.DebugWriteError(e);
            throw new RuntimeException("MinIO下载文件时报错" + e);
        }
    }

    public void deleteObject(String objectName) {
        createBucketIfNotExists(getMinioBucket());
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(getMinioBucket())
                            .object(objectName)
                            .build());
            Log.DebugWriteInfo("√ 对象删除成功.");
        } catch (Exception e) {
            Log.DebugWriteError(e);
            throw new RuntimeException("MinIO删除文件时报错" + e);
        }
    }

    public String getAccessPath(String objectName) {
        return getMinioHost() + "/" + getMinioBucket() + "/" + objectName;
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final javax.net.ssl.SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory,(X509TrustManager)(trustAllCerts[0]));
            builder.hostnameVerifier(new HostnameVerifier() {
                //这里存放不需要忽略SSL证书的域名，为空即忽略所有证书
                String[]ssls = {};
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (TextUtils.isEmpty(hostname)) {
                        return false;
                    }
                    return !Arrays.asList(ssls).contains(hostname);
                }
            });

            OkHttpClient okHttpClient = builder.connectTimeout(10, TimeUnit.MINUTES).
                    writeTimeout(10, TimeUnit.MINUTES).readTimeout(10, TimeUnit.MINUTES).retryOnConnectionFailure(true).build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
