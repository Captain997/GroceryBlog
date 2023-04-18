package com.epochge.controller.register.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail_demo_test{
    public static void main(String [] args) {
// 发送邮件
        sendEmail("2745601902@qq.com","854529");
    }
    private static void sendEmail(String registerEmail, String code) {
        // 收件人电子邮箱
        String to = registerEmail;

        // 发件人电子邮箱
        String from = "478292420@qq.com";

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
                return new PasswordAuthentication("478292420@qq.com", "zmxukesdnhzgbhje");
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
            message.setText("【Bpvank】验证码：" + code + "，您正在注册Bpvank博客账号，请勿将验证码告知他人，有效期3分钟，请妥善保管。");
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....Harmony");
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}