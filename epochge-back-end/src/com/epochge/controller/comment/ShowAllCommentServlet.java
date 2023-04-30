package com.epochge.controller.comment;

import com.epochge.entities.CommentInfo;
import com.epochge.service.CommentInfoService;
import com.epochge.util.CommentUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 查询所有评论
 */
@WebServlet("/showAllComment")
public class ShowAllCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        CommentInfoService service = new CommentInfoService();
        List<CommentInfo> allCommentList = service.getAllComment();
        List<CommentInfo> list = CommentUtil.recursionMethod(allCommentList);
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
