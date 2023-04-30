package com.epochge.controller.Article;

import com.epochge.service.ArticleInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 根据文章编号删除文章数据
 */
@WebServlet("/article/delete")
public class DeleteArticleInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        Integer articleId = Integer.valueOf(req.getParameter("articleId"));
        System.out.println(articleId);
        ObjectMapper mapper = new ObjectMapper();
        ArticleInfoService service = new ArticleInfoService();
        int i = service.deleteArticle(articleId);
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