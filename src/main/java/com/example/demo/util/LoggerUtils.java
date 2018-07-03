package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 19:46 2018/7/3
 */
public class LoggerUtils {

    private LoggerUtils() {}

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        
        String ip = request.getHeader("x-forward-for");
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.trim() == "" || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 多个路由时，取第一个非unknow的ip
        final String[] arr = ip.split(",");
        for(final String str : arr) {
            if (!"unknow".equalsIgnoreCase(str)) {
                ip = str;
                break;
            }
        }

        return ip;
    }

    /**
     * 判断是否为ajax请求
     * @return
     */
    public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }

}
