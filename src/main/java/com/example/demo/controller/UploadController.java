package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: NovaStar.tech
 * @Description:
 * @Date: 10:04 2018/7/6
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload() {
        return "upload";
    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST)
    public @ResponseBody String uploadFile(HttpServletRequest request, MultipartFile file) {
        System.out.println("aaa");
        try {
            // 上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            // 如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 调用上传方法
            uploadFile(uploadDir, file);
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    @RequestMapping(value = "/uploadfiles", method = RequestMethod.POST)
    public @ResponseBody String uploadFiles(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {
        System.out.println("aaa");
        try {
            // 上传目录地址
            String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";
            // 如果目录不存在，自动创建文件夹
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 遍历文件数组执行上传
            for (MultipartFile file : files) {
                if (file != null && !"".equalsIgnoreCase(file.getOriginalFilename())) {
                    // 调用上传方法
                    uploadFile(uploadDir, file);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
        return "上传成功";
    }

    /**
     * 上传单个文件
     * @param uploadDir 上传文件目录
     * @param file  上传对象
     */
    public void uploadFile(String uploadDir, MultipartFile file) throws Exception{

        // 文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String filename = UUID.randomUUID() + suffix;
        // 服务器端保存的文件对象
        File serverFile = new File(uploadDir + filename);

        System.out.println((serverFile.getAbsolutePath()));
        // 将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
    }
}
