package com.epochge.controller.classify;

import com.epochge.entities.ClassifyInfo;
import com.epochge.service.ClassifyInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 添加分类
 */
@WebServlet("/classify/insert")
public class InsertClassifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;

        //调用逻辑层service
        ClassifyInfoService service = new ClassifyInfoService();
        //接受请求体数据
        //1：读取JSON数据
        InputStreamReader reader = new InputStreamReader(req.getInputStream(), "utf-8");
        BufferedReader br = new BufferedReader(reader);
        //2:存入sb字符串
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        //3:将json字符串转换为User对象 注意User对象一定要有无参构造函数
        ObjectMapper ob = new ObjectMapper();
        //注意：接收的数据格式一定是  {username:'张三',password:'123456'}
        ClassifyInfo info = ob.readValue(sb.toString(), ClassifyInfo.class);
        int i = service.insertClassify(info);
        if (i == 1) {
            // 添加成功
            msg = "添加成功";
            code = 0;
        } else {
            // 注册失败
            msg = "添加成功";
            code = 1;
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("data", info);
        map.put("code", code);
        // 插入成功后将插入的数据传递给前端
        ob.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}