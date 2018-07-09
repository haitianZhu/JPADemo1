package com.example.demo.util;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: haitian
 * @Date: 2018/7/9 下午11:25
 * @Description:
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        // TODO Auto-generated method stub
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        // TODO Auto-generated method stub
        return encodedPassword.equals(rawPassword.toString());
    }
}
