package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 11:13 2018/7/2
 */
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromName;

    /**
     * 发送简单文本邮件
     * @param toAddress
     * @param title
     * @param content
     */
    public void sendSimpleMail(String toAddress, String title, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromName);
        message.setTo(toAddress);
        message.setSubject(title);
        message.setText(content);
        javaMailSender.send(message);
    }

    /**
     * 发送Html邮件
     * @param toAddress
     * @param title
     */
    public void sendHTMLMail(String toAddress, String title) {

        MimeMessage message = null;

        try {

            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(fromName);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(title);
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>大标题-h1</h1>")
                    .append("<p style='color:#F00'>红色字</p>")
                    .append("<p style='text-align:right'>右对齐</p>");
            messageHelper.setText(sb.toString(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }
}
