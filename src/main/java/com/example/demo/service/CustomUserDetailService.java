package com.example.demo.service;

import com.example.demo.controller.IndexController;
import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: haitian
 * @Date: 2018/7/9 下午11:22
 * @Description:
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserJPA userJPA;

    private final static Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userJPA.findUserEntityByUsername(username);
        if (user == null) {
            logger.error("未查询到用户：" + username + "信息！");
            throw new UsernameNotFoundException("未查询到用户：" + username + "信息！");
        }

        return user;
    }


}
