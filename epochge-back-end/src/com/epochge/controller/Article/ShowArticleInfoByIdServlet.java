package com.epochge.controller.Article;

import com.epochge.entities.ArticleInfo;
import com.epochge.service.ArticleInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 根据文章id获取文章信息
 */
@WebServlet("/article/showArticleInfo")
public class ShowArticleInfoByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的文章id
        Integer articleId = Integer.valueOf(req.getParameter("articleId"));

        //插入到数据库
        ArticleInfoService service = new ArticleInfoService();
        List<ArticleInfo> list = service.getAllArticleById(articleId);
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
