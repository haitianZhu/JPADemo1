package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

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

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromAddress;

    /**
     * 发送简单文本邮件
     * @param toAddress
     * @param title
     * @param content
     */
    public void sendSimpleMail(String toAddress, String title, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
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
    public void sendHTMLMail(String toAddress, String title, String htmlContent) {

        MimeMessage message = null;

        try {

            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(fromAddress);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(title);

            messageHelper.setText(htmlContent, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    /**
     * 发送带附件到邮件
     * @param toAddress
     * @param title
     * @param content
     */
    public void sendAttachmentsMail(String toAddress, String title, String content) {

        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(fromAddress);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(title);
            messageHelper.setText(content);

            FileSystemResource file = new FileSystemResource("src/main/resources/static/favicon.ico");

            messageHelper.addAttachment("图片.jpg", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    /**
     * 发送带静态资源的邮件
     * @param toAddress
     * @param title
     */
    public void sendInlineMail(String toAddress, String title) {

        MimeMessage message = null;

        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(fromAddress);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject(title);
            // 第二个参数指定发送的是HTML格式，同时cid:是固定的写法
            messageHelper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:picture' " +
                    "/></body></html>", true);


            FileSystemResource fileSystemResource = new FileSystemResource
                    ("src/main/resources/static/picture.jpg");

            messageHelper.addInline("picture", fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(message);
    }

    /**
     * 发送模板邮件
     * @param toAddress
     * @param title
     */
    public void sendTemplateMail(String toAddress, String title) {

        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);

        sendHTMLMail(toAddress, title, emailContent);
    }
}
