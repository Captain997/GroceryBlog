package com.epochge.controller.login;

import com.epochge.entities.BackstageMenuInfo;
import com.epochge.entities.UserInfo;
import com.epochge.service.BackstageMenuInfoService;
import com.epochge.service.UserInfoService;
import com.epochge.util.MenuTreeUtil;
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
 * 获取用户信息表所有数据，通过get请求到的数据，判断登录信息再用户信息表中是否存在
 */
@WebServlet("/login/showAllUserInfo")
public class ShowUserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msg = "";
        int code;
        List<BackstageMenuInfo> menuInfo = null;

        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取客户端传过来的用户名
        String loginName = req.getParameter("loginName");
        String loginPass = req.getParameter("loginPass");

        // 访问数据库信息
        UserInfoService service = new UserInfoService();
        BackstageMenuInfoService menuInfoservice = new BackstageMenuInfoService();
        // 查询到的当前登录用户的所有信息
        List<UserInfo> list = service.getAllUserInfo(loginName,loginPass);

        if (list.size()>0) { // 查询数据，用户登录信息没有查找到则List长度为0，反之大于0
            // 登录成功
            msg =  "登录成功";
            code = 0;
            // 通过用户权限获取，该权限的相关的后台菜单  list.get(0).getUserType()权限   0：管理员  1：普通用户
            if (list.get(0).getUserType() == 0){
                List<BackstageMenuInfo> allMenuTreeVoList  = menuInfoservice.getAllBackstageMenuInfo();
                List<BackstageMenuInfo> menuTreeVOTreeList = MenuTreeUtil.toTree(allMenuTreeVoList);
                menuInfo = menuTreeVOTreeList;
            }else if(list.get(0).getUserType() == 1){
                List<BackstageMenuInfo> allMenuTreeVoList  = menuInfoservice.getBackstageMenuInfo(list.get(0).getUserType());
                List<BackstageMenuInfo> menuTreeVOTreeList = MenuTreeUtil.toTree(allMenuTreeVoList);
                menuInfo = menuTreeVOTreeList;
            }
        } else {
            //登录失败
            msg = "登录失败，用户名或密码有误！";
            code = 1;
        }
        // 将map转换为json，并传递给客户端
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        map.put("data", list);
        map.put("code", code);
        map.put("menuInfo", menuInfo);
        mapper.writeValue(resp.getWriter(), map);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
