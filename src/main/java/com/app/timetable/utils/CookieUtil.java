package com.app.timetable.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * judithchen
 * 2019/4/1
 * Description：
 **/
public class CookieUtil {

    /**
     * 生成uuid的token
     * @return String
     */
    public static String getUUToken() {
        String token = UUID.randomUUID().toString().replace("-","").toLowerCase();
        return token;
    }

    /**
     *
     * @param key token的key值
     * @param toekn token具体的值
     * @param outTime cookie的超时时间，单位是秒
     * @param response 响应的response
     */
    public static void setTokenCookie(String key, String toekn, Integer outTime, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, toekn);
        cookie.setMaxAge(outTime);
        cookie.setDomain("");
        cookie.setPath("/");
        response.addCookie(cookie);
        response.setHeader("P3P", "CP='CURa ADMa DEVa PSAo PSDo OUR BUS UNI PUR INT DEM STA PRE COM NAV OTC NOI DSP COR'");
    }

    /**
     * 讲cookie用K,V存储
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 删除制定的cookie
     *
     * @param request
     * @param response
     * @param deleteKey
     * @throws NullPointerException
     */
    public static void delectCookieByName(HttpServletRequest request, HttpServletResponse response, String deleteKey) throws NullPointerException {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        for (String key : cookieMap.keySet()) {
            if (key.equals(deleteKey)) {
                Cookie cookie = cookieMap.get(key);
                //设置cookie有效时间为0
                cookie.setMaxAge(0);
                //不设置存储路径
                cookie.setPath("/");
                response.addCookie(cookie);
            }
        }
    }
}
