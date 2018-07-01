package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.jpa.UserJPA;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: haitian
 * @Date: 2018/7/1 下午8:21
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    @Autowired
    private UserJPA userJPA;

    @RequestMapping("/login")
    public String login(UserEntity user, HttpServletRequest request) {

        boolean loginRet = true;
        String result = "登陆成功";

        //根据用户名查询用户是否存在
        UserEntity userEntity = userJPA.findUserEntityByNameAndPwd(user.getName(), user.getPwd());

        if (userEntity == null) {
            // 用户不存在
            loginRet = false;
            result = "用户不存在，登陆失败";
        } else if (!userEntity.getPwd().equals(user.getPwd())) {
            // 密码错误
            loginRet = false;
            result = "用户密码不相符，登陆失败";
        }

        // 登陆成功
        if (loginRet) {
            // 将用户写入session
            request.getSession().setAttribute("_session_user", userEntity);
        }

        return result;
    }
}
