package com.ipplus360.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookie操作工具类
 * Created by 辉太郎 on 2017/8/28.
 */
public class CookieUtil {

    private final static int COOKIE_MAXAGE = -1;

    private CookieUtil() {

    }

    public static String getToken(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || StringUtils.isEmpty(name)) {
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        removeCookie(response, name,     null, null);
    }

    public static void removeCookie(HttpServletResponse response, String name, String path, String domain) {
        Cookie cookie = new Cookie(name, null);

        if (!StringUtils.isEmpty(path)) {
            cookie.setPath(path);
        }

        if (!StringUtils.isEmpty(domain)) {
            cookie.setDomain(domain);
        }

        cookie.setMaxAge(COOKIE_MAXAGE);
        response.addCookie(cookie);
    }
}