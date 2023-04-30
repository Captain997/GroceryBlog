package com.epochge.controller.email;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

/**
 * 发送邮件
 */
@WebServlet("/sendEmail")
public class SendEmail  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String msg = "";
        int code;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String registerEmail = req.getParameter("registerEmail");
        String content = req.getParameter("content");

        // 发送邮件
        // 收件人电子邮箱
        String to = registerEmail;

        // 发件人电子邮箱
        String from = "epochgenet@qq.com";

        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、授权码
                // 我的授权码gbuoutlxeriqjeae（写你自己）
                return new PasswordAuthentication("epochgenet@qq.com", "forpkiepfesmdfbe");
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("captain博客邮箱验证");
            // 设置消息体  发送的内容
            message.setText(content);
            // 发送消息
            Transport.send(message);
            // 发送成功
            System.out.println("Sent message successfully....Harmony");
            msg =  "发送成功";
            code = 0;
        }catch (MessagingException mex) {
            mex.printStackTrace();
            // 发送失败
            System.out.println("Failed to send message...");
            msg =  "发送失败";
            code = 1;
        }
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        // 发送后操作信息传递给前端
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}