package com.epochge.controller.user;

import com.epochge.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 根据用户id修改用户基础信息：用户名、签名、头像
 * @author Bpvank
 */
@WebServlet("/user/updateUserBasicsInfo")
public class UpdateUserBasicsInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的值
        // Integer userId, String userName, String userSignature, String userIcon
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        String userName = req.getParameter("userName");
        String userSignature = req.getParameter("userSignature");
        String userIcon = req.getParameter("userIcon");

        // 数据库
        UserInfoService service = new UserInfoService();
        int i = service.updateUserBasicsInfo(userId, userName, userSignature, userIcon);
        if (i == 1) {
            msg = "修改成功";
            code = 0;
        } else {
            msg = "修改失败";
            code = 1;
        }
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

