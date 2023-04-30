package com.epochge.controller.register;

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
 * 获取前端生成的验证码，发送给注册用户邮箱
 */
@WebServlet("/register/registerGetCode")
public class RegisterGetCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String msg = "";
        int code;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String registerEmail = req.getParameter("registerEmail");
        String verificationCode = req.getParameter("verificationCode");

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
                // 我的授权码
                return new PasswordAuthentication("epochgenet@qq.com", "zmxukesdnhzgbhje");
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
            message.setSubject("Bpvank博客邮箱验证");
            // 设置消息体
            message.setText("【Bpvank博客】验证码：" + verificationCode + "，请勿将验证码告知他人，有效期3分钟，请妥善保管。");
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
