package com.example.demo.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.demo.entity.LoggerEntity;
import com.example.demo.jpa.LoggerJPA;
import com.example.demo.util.LoggerUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 19:24 2018/7/3
 */
public class LoggerInterceptor implements HandlerInterceptor {

    // 请求开始时间标识
    private static final String LOGGER_SEND_TIME = "_send_time";

    // 请求日志实体标识
    private static final String LOGGER_ENTITY = "_logger_entity";

    public static final String LOGGER_RETURN = "_logger_return";

    /**
     * 进入SpringMVC的Controller之前开始记录日志实体
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        // 记录日志实体
        LoggerEntity loggerEntity = new LoggerEntity();
        // 获取请求sessionId
        String sessionId = request.getRequestedSessionId();
        // 请求路径
        String url = request.getRequestURI();
        // 获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(), SerializerFeature
                .DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);

        // 设置客户端ip
        loggerEntity.setClientIp(LoggerUtils.getClientIP(request));
        // 设置请求方法
        loggerEntity.setMethod(request.getMethod());
        // 设置请求类型(ajax, 普通请求)
        loggerEntity.setType(LoggerUtils.getRequestType(request));
        // 设置请求参数内容json字符串
        loggerEntity.setParamData(paramData);
        // 设置请求地址
        loggerEntity.setUri(url);
        // 设置sessionId
        loggerEntity.setSessionId(sessionId);

        // 设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME, System.currentTimeMillis());
        // 设置请求实体到request内, 方便afterCompletion方法调用
        request.setAttribute(LOGGER_ENTITY, loggerEntity);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // 获取请求错误码
        int status = response.getStatus();
        long currentTime = System.currentTimeMillis();
        long time = Long.valueOf(request.getAttribute(LOGGER_SEND_TIME).toString());

        // 记录本次日志实体
        LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        // 设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf((currentTime - time) + ""));
        // 设置返回时间
        loggerEntity.setReturnTime(currentTime + "");
        // 设置返回错误码
        loggerEntity.setHttpStatusCode(status + "");
        // 设置返回值
        loggerEntity.setReturnData(JSON.toJSONString(request.getAttribute(LOGGER_RETURN), SerializerFeature
                .DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue));

        // 将日志写入数据库
        LoggerJPA loggerJPA = getDAO(LoggerJPA.class, request);
        loggerJPA.save(loggerEntity);

    }

    /**
     * 根据传入的类型获取spring管理的对应dao
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    private <T> T getDAO(Class<T> clazz, HttpServletRequest request) {

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return factory.getBean(clazz);
    }
}
