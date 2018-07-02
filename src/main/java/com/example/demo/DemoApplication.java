package com.example.demo;

import com.example.demo.service.MailSenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
public class DemoApplication {

    @Autowired
    private MailSenderService mailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @RequestMapping("/test")
    public void test() {
        System.out.println("testMail");
//        mailSenderService.sendSimpleMail("nova_zhuht@126.com", "测试标题", "测试内容" + new Date().toString());

//        StringBuffer sb = new StringBuffer();
//        sb.append("<h1>大标题-h1</h1>")
//                .append("<p style='color:#F00'>红色字</p>")
//                .append("<p style='text-align:right'>右对齐</p>");
//        mailSenderService.sendHTMLMail("nova_zhuht@126.com", "测试标题:HTML邮件", sb.toString());

//        mailSenderService.sendAttachmentsMail("nova_zhuht@126.com", "测试标题：带附件", "测试内容" + new Date()
//                .toString());

//        mailSenderService.sendInlineMail("nova_zhuht@126.com", "测试标题：带静态资源的邮件");

        mailSenderService.sendTemplateMail("nova_zhuht@126.com", "测试标题:模板邮件");
    }
}
