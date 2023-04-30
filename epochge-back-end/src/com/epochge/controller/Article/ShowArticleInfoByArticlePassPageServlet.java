package com.epochge.controller.Article;

import com.epochge.entities.ArticleInfo;
import com.epochge.entities.PageBean;
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
 * 下拉框筛选文章审核状态，查询出相关内容并进行分页
 */
@WebServlet("/article/search/pass")
public class ShowArticleInfoByArticlePassPageServlet extends HttpServlet {
    ArticleInfoService service = new ArticleInfoService();
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
        Integer articlePass = Integer.valueOf(request.getParameter("articlePass")); // 审核状态
        System.out.println(currentPage + pageSize + userType + articlePass);
        //2：调用service查询
        PageBean<ArticleInfo> pb = null;
        if (userType == 0) { // 管理员
            pb = service.findStudentByPage3(currentPage, pageSize, articlePass, null);//得到分页输出结果
        } else {// 普通用户
            pb = service.findStudentByPage3(currentPage, pageSize, articlePass, userId);//得到分页输出结果
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
