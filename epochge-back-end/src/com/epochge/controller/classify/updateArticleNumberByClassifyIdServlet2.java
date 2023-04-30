package com.epochge.controller.classify;

import com.epochge.service.ClassifyInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 根据分类id减少文章数量
 */
@WebServlet("/updateArticleNumberByClassifyId2")
public class updateArticleNumberByClassifyIdServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的分类id
        Integer classifyId = Integer.valueOf(req.getParameter("classifyId"));
        // 数据库
        ClassifyInfoService service = new ClassifyInfoService();
        int i = service.updateArticleNumber2(classifyId);
        if (i == 1){
            msg = "修改成功";
            code = 0;
        }else{
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
        this.doGet(req,resp);
    }
}