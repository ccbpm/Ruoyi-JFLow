package bp.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import bp.sys.SFDBSrc;
import com.google.gson.Gson;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import bp.da.DataType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);


    public static String doGet(String url, String data, String header, String context1) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        HttpGet httpGet = new HttpGet(url);
        if (!DataType.IsNullOrEmpty(header) && !DataType.IsNullOrEmpty(context1)) {
            httpGet.setHeader(header, context1);
        }
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else {
                logger.error("执行 GET 请求至 {} 失败，状态码: {}", url, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("执行 GET 请求至 {} 时发生错误", url, e);
        } catch (Exception e) {
            logger.error("执行 GET 请求至 {} 时发生意外错误", url, e);
        }
        return resultString;
    }

    public static String doGetSSL(String url, Map<String, String> headerMap, Map<String, String> param) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        URI uri = null;
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            uri = builder.build();
        } catch (URISyntaxException e) {
            logger.error("为 GET SSL 请求构建 URI 时出错: {}", url, e);
            return "";
        }

        HttpGet httpGet = new HttpGet(uri);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else {
                logger.error("执行 GET SSL 请求至 {} 失败，状态码: {}", uri, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException e) {
            logger.error("执行 GET SSL 请求至 {} 时发生错误", uri, e);
        } catch (Exception e) {
            logger.error("执行 GET SSL 请求至 {} 时发生意外错误", uri, e);
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null, null, null);
    }

    public static String doPostSSL(String url, String Json, Map<String, String> headerParam) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        HttpPost httpPost = getHttpPost(url, Json, headerParam);
        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {
                logger.info("执行 POST SSL 请求至 {} 时发生重定向，状态码: {}", url, statusCode);
                resultString = "";
                EntityUtils.consumeQuietly(response.getEntity());
            } else {
                logger.error("执行 POST SSL 请求至 {} 失败，状态码: {}", url, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException e) {
            logger.error("执行 POST SSL 请求至 {} 时发生错误", url, e);
        } catch (Exception e) {
            logger.error("执行 POST SSL 请求至 {} 时发生意外错误", url, e);
        }
        return resultString;
    }

    private static HttpPost getHttpPost(String url, String Json, Map<String, String> headerParam) {
        HttpPost httpPost = new HttpPost(url);

        httpPost.setHeader("Content-Type", "application/json");
        handleParams(Json, headerParam, httpPost);
        return httpPost;
    }

    public static String doPost(String url, Map<String, String> param, String header, String context1) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);

        if (!DataType.IsNullOrEmpty(header) && !DataType.IsNullOrEmpty(context1)) {
            httpPost.setHeader(header, context1);
        }

        if (param != null && !param.isEmpty()) {
            List<NameValuePair> paramList = new ArrayList<>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            try {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, Consts.UTF_8);
                httpPost.setEntity(entity);
            } catch (Exception e) {
                logger.error("为 POST 请求至 {} 编码表单参数时出错", url, e);
                return "";
            }
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {
                logger.info("执行 POST (带 context) 请求至 {} 时发生重定向，状态码: {}", url, statusCode);
                resultString = "";
                EntityUtils.consumeQuietly(response.getEntity());
            } else {
                logger.error("执行 POST (带 context) 请求至 {} 失败，状态码: {}", url, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException e) {
            logger.error("执行 POST (带 context) 请求至 {} 时发生错误", url, e);
        } catch (Exception e) {
            logger.error("执行 POST (带 context) 请求至 {} 时发生意外错误", url, e);
        }
        return resultString;
    }

    public static String doPost(String url, String Json, Map<String, String> headerParam) throws Exception {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            logger.error("为 POST 请求 (带 JSON) 提供无效的 URI 语法: {}", url, e);
            throw new Exception("URL 语法无效: " + url, e);
        }

        HttpPost httpPost = new HttpPost(uri);

        handleParams(Json, headerParam, httpPost);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else if (statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY) {
                logger.info("执行 POST (带 JSON) 请求至 {} 时发生重定向，状态码: {}", url, statusCode);
                resultString = "";
                EntityUtils.consumeQuietly(response.getEntity());
            } else {
                String errorMsg = String.format("执行 POST (带 JSON) 请求至 %s 失败，状态码: %d", url, statusCode);
                logger.error(errorMsg);
                EntityUtils.consumeQuietly(response.getEntity());
                throw new Exception(errorMsg);
            }
        } catch (IOException e) {
            logger.error("执行 POST (带 JSON) 请求至 {} 时发生 IO 错误", url, e);
            throw e;
        } catch (Exception e) {
            logger.error("执行 POST (带 JSON) 请求至 {} 时发生意外错误", url, e);
            if (!(e instanceof URISyntaxException)) {
                throw e;
            } else {
                throw new Exception("执行 POST 请求时出错", e);
            }
        }
        return resultString;
    }

    private static void handleParams(String Json, Map<String, String> headerParam, HttpPost httpPost) {
        if (headerParam != null) {
            for (Map.Entry<String, String> entry : headerParam.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (Json != null) {
            StringEntity entity = new StringEntity(Json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }
    }

    public static String doPost(String url) {
        return doPost(url, null, null, null);
    }
    public static String doPostJson(String url, String json) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        String resultString = "";
        HttpPost httpPost = new HttpPost(url);

        if (json != null) {
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode >= HttpStatus.SC_OK && statusCode < HttpStatus.SC_MULTIPLE_CHOICES) {
                resultString = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
            } else {
                logger.error("执行 POST JSON (简化版) 请求至 {} 失败，状态码: {}", url, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException e) {
            logger.error("执行 POST JSON (简化版) 请求至 {} 时发生错误", url, e);
        } catch (Exception e) {
            logger.error("执行 POST JSON (简化版) 请求至 {} 时发生意外错误", url, e);
        }
        return resultString;
    }

    /**
     * 下载文件到指定路径。(保留原始签名)
     */
    public static boolean HttpDownloadFile(String urlPath, String toPath) {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        HttpGet httpGet = new HttpGet(urlPath);
        boolean success = false;

        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_OK) {
                try (InputStream inputStream = response.getEntity().getContent();
                     FileOutputStream fileOutputStream = new FileOutputStream(toPath)) {
                    byte[] buffer = new byte[8192];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    success = true;
                    logger.info("文件已成功下载从 {} 到 {}", urlPath, toPath);
                } catch (IOException e) {
                    logger.error("将文件写入 {} 时出错 (源: {})", toPath, urlPath, e);
                }
            } else {
                logger.error("从 {} 下载文件失败，状态码: {}", urlPath, statusCode);
                EntityUtils.consumeQuietly(response.getEntity());
            }
        } catch (IOException | IllegalArgumentException e) {
            logger.error("从 {} 下载文件时发生错误", urlPath, e);
        } catch (Exception e) {
            logger.error("从 {} 下载文件时发生意外错误", urlPath, e);
        }
        return success;
    }

    public static String HttpPostConnect_Data(String dbSrNo, String exp, Hashtable<String, Object> ht, String requestMethod) throws Exception {
        String apiUrl = exp;
        try {
            if (apiUrl.contains("@WebApiHost")) {
                apiUrl = apiUrl.replace("@WebApiHost", bp.difference.SystemConfig.GetValByKey("WebApiHost", ""));
            }

            SFDBSrc mysrc = new SFDBSrc(dbSrNo);
            String baseUrl = mysrc.getConnString();
            if (baseUrl.endsWith("/") && apiUrl.startsWith("/")) {
                apiUrl = baseUrl + apiUrl.substring(1);
            } else if (!baseUrl.endsWith("/") && !apiUrl.startsWith("/")) {
                apiUrl = baseUrl + "/" + apiUrl;
            } else {
                apiUrl = baseUrl + apiUrl;
            }
            if (apiUrl.contains("{") && ht != null) {
                for (Map.Entry<String, Object> entry : ht.entrySet()) {
                    if (entry.getValue() != null) {
                        apiUrl = apiUrl.replace("{" + entry.getKey() + "}", entry.getValue().toString());
                    }
                }
            }
        } catch (Exception e) {
            logger.error("构建 HttpPostConnect_Data 的 API URL 时出错 (dbSrNo={}, exp={})", dbSrNo, exp, e);
            throw new Exception("构建 API URL 失败", e);
        }

        String apiHost;
        int queryIndex = apiUrl.indexOf('?');
        if (queryIndex != -1) {
            apiHost = apiUrl.substring(0, queryIndex);
            String queryString = apiUrl.substring(queryIndex + 1);
            logger.warn("在 HttpPostConnect_Data URL 中检测到查询字符串，但目标方法 doPostJson 通常使用 Body: {}", queryString);
        } else {
            apiHost = apiUrl;
        }

        String jsonBody = "{}";
        if (ht != null && !ht.isEmpty()) {
            try {
                Gson gson = new Gson();
                jsonBody = gson.toJson(ht);
            } catch (Exception e) {
                logger.error("序列化 Hashtable 为 JSON 时失败 (HttpPostConnect_Data)", e);
                throw new Exception("序列化请求数据失败", e);
            }
        }
        return doPost(apiHost, jsonBody, null);
    }
}