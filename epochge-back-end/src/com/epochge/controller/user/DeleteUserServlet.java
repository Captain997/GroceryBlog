package com.epochge.controller.user;

import com.epochge.service.ArticleInfoService;
import com.epochge.service.CommentInfoService;
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
 * 根据用户id删除用户    同时删除用户的所有评论以及文章
 */
@WebServlet("/user/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        Integer userId = Integer.valueOf(req.getParameter("userId"));
        ObjectMapper mapper = new ObjectMapper();
        // 删除用户的文章
        ArticleInfoService service3 = new ArticleInfoService();
        int i3 = service3.deleteArticleByUserId(userId);
        // 删除用户的评论
        CommentInfoService service2 = new CommentInfoService();
        int i2 = service2.deleteCommentByUserId(userId);
        // 删除用户信息
        UserInfoService service = new UserInfoService();
        int i = service.deleteUser(userId);
        if (i > 0) {
            // 删除成功
            msg = "删除成功";
            code = 0;
        } else {
            // 删除失败
            msg = "删除失败";
            code = 1;
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("code", code);
        mapper.writeValue(resp.getWriter(), map);
    }
}