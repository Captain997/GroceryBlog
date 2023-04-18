package com.epochge.controller.url;

import com.epochge.entities.BackstageMenuInfo;
import com.epochge.entities.UrlInfo;
import com.epochge.service.UrlInfoService;
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
 * 根据链接类型传值
 * @author Bpvank
 */
@WebServlet("/url/showAllUrlInfo")
public class ShowUrlInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        List<BackstageMenuInfo> menuInfo = null;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的类型
        Integer urlType = Integer.valueOf(req.getParameter("urlType"));

        // 访问数据库信息
        UrlInfoService service = new UrlInfoService();
        // 查询到的当前类型所有的链接
        List<UrlInfo> list = service.getUrlInfoByUrlTyep(urlType);

        if (list.size() > 0) { // 查询数据，用户登录信息没有查找到则List长度为0，反之大于0
            // 查询成功
            msg = "查询成功";
            code = 0;
        } else {
            // 查询失败
            msg = "查询失败";
            code = 1;
        }
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("data", list);
        map.put("code", code);
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

