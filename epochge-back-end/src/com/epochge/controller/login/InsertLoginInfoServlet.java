package com.epochge.controller.login;

import com.epochge.service.LoginInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 添加登录信息
 */
@WebServlet("/login/insertLoginInfo")
public class InsertLoginInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        //调用逻辑层service
        LoginInfoService service = new LoginInfoService();
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        int i = service.insertLoginInfo(userId);
        if (i == 1){
            // 添加成功
            msg =  "添加成功";
            code = 0;
        }else{
            // 注册失败
            msg =  "添加成功";
            code = 1;
        }
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        // 插入成功后将插入的数据传递给前端
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}


