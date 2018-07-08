package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: haitian
 * @Date: 2018/7/1 下午9:13
 * @Description:
 */
@Controller
@RequestMapping("/user")
public class IndexController {

    // logback
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/login_view", method = RequestMethod.GET)
    public String login_view() {
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {

        logger.debug("访问index方法-debug");
        logger.info("访问index方法-info");
        logger.error("访问index方法-error");

        return "index";
    }
}
