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
 * 根据文章id删除分类
 */
@WebServlet("/classify/delete")
public class DeleteClassifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        Integer classifyId = Integer.valueOf(req.getParameter("classifyId"));
        System.out.println(classifyId);
        ObjectMapper mapper = new ObjectMapper();
        ClassifyInfoService service = new ClassifyInfoService();
        int i = service.deleteClassify(classifyId);
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