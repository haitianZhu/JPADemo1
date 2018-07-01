package com.example.demo;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: haitian
 * @Date: 2018/7/1 下午9:19
 * @Description:
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(request.getRequestURI());
        //登录不做拦截
        if(request.getRequestURI().equals("/user/login") || request.getRequestURI().equals
                ("/user/login_view")) {
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("_session_user");
        if(obj == null)
        {
            response.sendRedirect("/user/login_view");
            return false;
        }
        return true;
    }
}
