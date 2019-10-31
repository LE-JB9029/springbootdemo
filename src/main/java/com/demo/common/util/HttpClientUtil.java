package com.demo.common.util;

import jodd.util.StringUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientUtil {

    /**
     * post请求，自定义请求头（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String params, Integer socketTimeout, Integer connectTimeout, Map<String, String> headerMap) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        for (String key : headerMap.keySet()) {
            httpPost.setHeader(key, headerMap.get(key));
        }
        String charSet = "UTF-8";
        if (StringUtil.isNotEmpty(params)) {
            StringEntity entity = new StringEntity(params, charSet);
            httpPost.setEntity(entity);
        }
        if (socketTimeout == null) {
            socketTimeout = 5000;
        }
        if (connectTimeout == null) {
            connectTimeout = 5000;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置传输超时时间
        httpPost.setConfig(requestConfig);
        return sendRequestAndGetResponse(httpPost);
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String params, Integer socketTimeout, Integer connectTimeout) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "*/*");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        String charSet = "UTF-8";
        if (StringUtil.isNotEmpty(params)) {
            StringEntity entity = new StringEntity(params, charSet);
            httpPost.setEntity(entity);
        }
        if (socketTimeout == null) {
            socketTimeout = 5000;
        }
        if (connectTimeout == null) {
            connectTimeout = 5000;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置传输超时时间
        httpPost.setConfig(requestConfig);
        return sendRequestAndGetResponse(httpPost);
    }

    /**
     * @param url
     * @param paramMap
     * @return
     * @throws Exception
     */
    public static String doGet(String url, Map<String, Object> paramMap, Integer socketTimeout, Integer connectTimeout) throws Exception {
        StringBuffer sb = new StringBuffer("?t=" + System.currentTimeMillis());
        if (paramMap != null) {
            for (String key : paramMap.keySet()) {
                sb.append("&" + key + "=" + paramMap.get(key));
            }
        }
        HttpGet httpget = new HttpGet(url + sb.toString());
        if (socketTimeout == null) {
            socketTimeout = 5000;
        }
        if (connectTimeout == null) {
            connectTimeout = 5000;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();//设置传输超时时间
        httpget.setConfig(requestConfig);
        return sendRequestAndGetResponse(httpget);
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    private static String sendRequestAndGetResponse(HttpUriRequest request) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(request);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
