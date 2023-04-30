package com.epochge.controller.comment;

import com.epochge.entities.CommentInfo;
import com.epochge.entities.PageBean;
import com.epochge.service.CommentInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * 后台评论管理：分页查询  + 搜索框内容  管理员获取全部  普通用户获取自己的
 */
@WebServlet("/comment/page/byUserType")
public class ShowCommentInfoPageServlet extends HttpServlet {
    CommentInfoService service = new CommentInfoService();
    //转换为json数据
    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        //1、获取参数
        Integer currentPage = Integer.valueOf(request.getParameter("currentPage"));//当前的页码
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));//每页显示的条数
        Integer userType = Integer.valueOf(request.getParameter("userType")); // 用户类型
        Integer userId = Integer.valueOf(request.getParameter("userId")); // 用户id
        String searchContent = request.getParameter("searchContent");// 搜索内容
        //2：调用service查询
        PageBean<CommentInfo> pb = null;
        if (userType == 0) { // 管理员
            pb = service.findStudentByPage(currentPage, pageSize, null, searchContent);//得到分页输出结果
        } else {// 普通用户
            pb = service.findStudentByPage(currentPage, pageSize, userId, searchContent);//得到分页输出结果
        }
        HashMap<Object, Object> map = new HashMap<>();

        if (pb != null) {
            // 成功
            msg = "成功";
            map.put("data", pb);
            code = 0;
        } else {
            // 失败
            msg = "失败";
            code = 1;
        }
        map.put("msg", msg);
        map.put("code", code);
        //3:把pageBean存入request
        mapper.writeValue(resp.getWriter(), map);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
