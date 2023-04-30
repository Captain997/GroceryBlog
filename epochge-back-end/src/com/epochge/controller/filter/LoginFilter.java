package com.epochge.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        // 0:强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        // 统一解决乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        //1:获取资源 的请求路径
        String uri = request.getRequestURI();
        //2:判断是否包含登录资源相关路径
        if(uri.contains("/user/login")|| uri.contains("/index")){
            //包含 放行
            chain.doFilter(req, resp);
        }else{
            //不包含login ，需要验证用户是否登录
            //3、从session中获取user的值
            Object user = request.getSession().getAttribute("user");
            if(user != null){
                //已经登录，放行
                chain.doFilter(req, resp);
            }else{
                //没有登录，跳转到登录页面
                request.setAttribute("login_msg","您尚未登录，请登录。。。");
                request.getRequestDispatcher("/user/login").forward(request,resp);

            }

        }
       
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
