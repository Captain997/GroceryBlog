package com.epochge.controller.login;

import com.epochge.entities.BackstageMenuInfo;
import com.epochge.service.BackstageMenuInfoService;
import com.epochge.util.MenuTreeUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 获取用户信息表所有数据，通过get请求到的数据，判断登录信息再用户信息表中是否存在
 * @author Bpvank
 */
@WebServlet("/showBackstageMenuInfo")
public class ShowBackstageMenuInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        BackstageMenuInfoService service = new BackstageMenuInfoService();
        List<BackstageMenuInfo> allMenuTreeVoList  = service.getAllBackstageMenuInfo();
        List<BackstageMenuInfo> menuTreeVOTreeList = MenuTreeUtil.toTree(allMenuTreeVoList);
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(), menuTreeVOTreeList);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
