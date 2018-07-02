package com.example.demo.util;

import org.apache.tomcat.jni.Local;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 11:17 2018/7/2
 */
public class PropertiesUtil {

    private final ResourceBundle resourceBundle;
    private final String fileName;


    /**
     * 获取文件资源对象
     * @param fileName
     */
    public PropertiesUtil(String fileName) {
        this.fileName = fileName;
        Locale locale = new Locale("zh", "CN");
        this.resourceBundle = ResourceBundle.getBundle(this.fileName, locale);
    }

    /**
     * 根据key，获取value
     * @param key
     * @return
     */
    public String getValue(String key) {
        String message = this.resourceBundle.getString(key);
        return message;
    }

    /**
     * 获取properties文件内所有key值
     * @return
     */
    public Enumeration<String> getKeys() {
        return resourceBundle.getKeys();
    }
}
