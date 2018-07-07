package com.example.demo.controller;

import com.example.demo.entity.ValidatorEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

/**
 * @Author: haitian
 * @Date: 2018/7/7 上午10:36
 * @Description:
 */
@RestController
public class ValidatorController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/validator")
    public String validator(@Valid ValidatorEntity entity, BindingResult result) {

        if (result.hasErrors()) {

            StringBuffer stringBuffer = new StringBuffer();
            // 获取错误字段集合
            List<FieldError> fieldErrors = result.getFieldErrors();
            // 获取本地Locale
            Locale currentLocale = LocaleContextHolder.getLocale();

            // 遍历错误字段获取错误消息
            for (FieldError fieldError : fieldErrors) {

                // 获取错误信息
                String errorMessage = messageSource.getMessage(fieldError, currentLocale);
                // 添加到错误消息集合内
                stringBuffer.append(fieldError.getField() + ":" + errorMessage + ",");
            }

            return stringBuffer.toString();
        }

        return "验证成功，" + "名称：" + entity.getName() + "年龄：" + entity.getAge() + "邮箱地址：" + entity.getMail();
    }
}
