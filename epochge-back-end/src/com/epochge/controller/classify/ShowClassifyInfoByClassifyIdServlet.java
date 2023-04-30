package com.epochge.controller.classify;

import com.epochge.entities.ClassifyInfo;
import com.epochge.service.ClassifyInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 根据分类id查询分类信息
 */
@WebServlet("/classify/showClassifyInfoByClassifyId")
public class ShowClassifyInfoByClassifyIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的文章id
        Integer classifyId = Integer.valueOf(req.getParameter("classifyId"));

        //插入到数据库
        ClassifyInfoService service = new ClassifyInfoService();
        List<ClassifyInfo> list = service.getClassifyInfoByClassifyId(classifyId);
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}