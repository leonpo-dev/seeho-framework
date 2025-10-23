package com.seeho.util;

import com.seeho.enums.ErrorCode;
import com.seeho.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.net.URIBuilder;
import org.apache.hc.core5.util.Timeout;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 通用 HTTP 客户端工具类（基于 Apache HttpClient 5.x）
 *
 * @author Leonpo
 * @since 2025-10-23
 */
@Slf4j
public class HttpClientUtil {
    // ===== 基础配置 =====
    private static final Timeout TIMEOUT = Timeout.ofMilliseconds(5000); // 毫秒
    private static final int MAX_TOTAL = 100;
    private static final int MAX_PER_ROUTE = 20;

    // 懒加载 HttpClient 实例（volatile + double check）
    private static volatile CloseableHttpClient httpClient;

    private HttpClientUtil() {
        // 工具类禁止实例化
    }

    /**
     * 获取单例 HttpClient（按需懒加载）
     */
    private static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            synchronized (HttpClientUtil.class) {
                if (httpClient == null) {
                    PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
                    manager.setMaxTotal(MAX_TOTAL);
                    manager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

                    RequestConfig config = RequestConfig.custom()
                            .setConnectTimeout(TIMEOUT)
                            .setConnectionRequestTimeout(TIMEOUT)
                            .setResponseTimeout(TIMEOUT)
                            .build();

                    httpClient = HttpClients.custom()
                            .setConnectionManager(manager)
                            .setDefaultRequestConfig(config)
                            .build();

                    log.info("HttpClient initialized lazily (maxTotal={}, perRoute={}, timeout={}ms)",
                            MAX_TOTAL, MAX_PER_ROUTE, TIMEOUT);
                }
            }
        }
        return httpClient;
    }

    /**
     * 发送 GET 请求
     */
    public static String doGet(String url) {
        HttpGet get = new HttpGet(url);
        try (CloseableHttpResponse response = getHttpClient().execute(get)) {
            int status = response.getCode();
            String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (status >= 200 && status < 300) {
                return result;
            } else {
                log.warn("GET {} failed: status={}, body={}", url, status, result);
                return null;
            }
        } catch (Exception e) {
            log.error("GET {} error", url, e);
            throw new BusinessException(e, ErrorCode.HTTP_ERROR);
        }
    }

    public static String doGet(String url, Map<String, String> params) {
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                params.forEach(builder::addParameter);
            }

            HttpGet get = new HttpGet(builder.build());
            try (CloseableHttpResponse response = getHttpClient().execute(get)) {
                int status = response.getCode();
                String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                if (status >= 200 && status < 300) {
                    return result;
                } else {
                    log.warn("GET {} failed: status={}, body={}", builder, status, result);
                    return null;
                }
            }
        } catch (Exception e) {
            log.error("GET {} error", url, e);
            return null;
        }
    }

    /**
     * 发送 POST 请求（JSON）
     */
    public static String doPostJson(String url, String jsonBody) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try (CloseableHttpResponse response = getHttpClient().execute(post)) {
            int status = response.getCode();
            String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (status >= 200 && status < 300) {
                return result;
            } else {
                log.warn("POST {} failed: status={}, body={}", url, status, result);
                return null;
            }
        } catch (Exception e) {
            log.error("POST {} error", url, e);
            throw new BusinessException(e, ErrorCode.HTTP_ERROR);
        }
    }

    /**
     * 发送 POST 请求（Form表单）
     */
    public static String doPostForm(String url, Map<String, String> params) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        List<NameValuePair> formParams = new ArrayList<>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }

        post.setEntity(new UrlEncodedFormEntity(formParams, StandardCharsets.UTF_8));

        try (CloseableHttpResponse response = getHttpClient().execute(post)) {
            int status = response.getCode();
            String result = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
            if (status >= 200 && status < 300) {
                return result;
            } else {
                log.warn("POST {} failed: status={}, body={}", url, status, result);
                return null;
            }
        } catch (Exception e) {
            log.error("POST {} error", url, e);
            throw new BusinessException(e, ErrorCode.HTTP_ERROR);
        }
    }

    /**
     * 手动关闭 HttpClient（一般无需调用）
     */
    public static void close() {
        if (httpClient != null) {
            synchronized (HttpClientUtil.class) {
                if (httpClient != null) {
                    try {
                        httpClient.close();
                        httpClient = null;
                        log.info("HttpClient closed manually");
                    } catch (IOException e) {
                        log.error("Error closing HttpClient", e);
                    }
                }
            }
        }
    }
}
