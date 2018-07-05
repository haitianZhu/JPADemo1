package com.example.demo.configuration;

import com.example.demo.servlet.TestServlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: haitian
 * @Date: 2018/7/5 下午11:05
 * @Description:
 */
@Configuration
public class ServletConfiguration {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new TestServlet(), "/test");
    }
}
