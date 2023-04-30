package com.epochge.controller.comment;

import com.epochge.service.CommentInfoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 根据文章id删除评论
 */
@WebServlet("/comment/deleteByArticleId")
public class DeleteCommentByArticleIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer articleId = Integer.valueOf(req.getParameter("articleId"));
        System.out.println(articleId);
        CommentInfoService service = new CommentInfoService();
        int i = service.deleteCommentByArticleId(articleId);
    }
}

