package com.example.demo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: haitian
 * @Date: 2018/7/5 下午10:57
 * @Description:
 */
@WebServlet(urlPatterns = "/test2")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 设置返回类型为json
        resp.setContentType("application/json");
        // 设置返回字符集
        resp.setCharacterEncoding("utf-8");
        // 输出对象
        PrintWriter writer = resp.getWriter();
        // 输出消息
        writer.write("执行TestServlet" +
                "内doGet方法成功！");
        writer.close();
    }
}
