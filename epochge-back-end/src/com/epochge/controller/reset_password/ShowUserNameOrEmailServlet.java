package com.epochge.controller.reset_password;

import com.epochge.entities.UserInfo;
import com.epochge.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 查询用户注册的邮箱数据库是否存在
 */
@WebServlet("/resetPassword/showUserNameOrEmail")
public class ShowUserNameOrEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的邮箱
        String userNameOrEmail = req.getParameter("userNameOrEmail");
        // 数据库
        UserInfoService service = new UserInfoService();
        List<UserInfo> existUserNameOrEmail = service.isExistuserNameOrEmail(userNameOrEmail);
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        if (existUserNameOrEmail.size() == 0){
            msg = "用户名或邮箱未存在";
            code = 1;
        }else{
            msg = "用户名或邮箱存在";
            code = 0;
            map.put("data",existUserNameOrEmail.get(0));
        }
        map.put("msg", msg);

        map.put("code", code);
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }


}
