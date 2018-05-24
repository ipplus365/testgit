package com.ipplus360.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by 辉太郎 on 2017/8/29.
 */
public class RestTemplateUtil {

    private final static RestTemplate template = new RestTemplate();

    public static String get(ServletRequest req, String url, Map<String, ?> params) {
        ResponseEntity<String> res = request(req, url, HttpMethod.GET, params);
        return res.getBody();
    }

    public static String post(ServletRequest req, String url, Map<String, ?> params) {
        ResponseEntity<String> res = request(req, url, HttpMethod.POST, params);
        return res.getBody();
    }

    private static ResponseEntity<String> request(ServletRequest req, String url, HttpMethod method, Map<String, ?> params) {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            requestHeaders.add(key, value);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(params != null ? JSON.toJSONString(params) : null, requestHeaders);
        ResponseEntity<String> res = template.exchange(url, method, requestEntity, String.class);
        return res;
    }


}
