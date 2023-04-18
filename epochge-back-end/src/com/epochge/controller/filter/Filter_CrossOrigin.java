package com.epochge.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Bpvank
 */
@WebFilter(filterName = "Filter_CrossOrigin",urlPatterns = "/*")
public class Filter_CrossOrigin implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException, IOException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
//        request1.getHeader("origin")
//        response1.setHeader("Set-Cookie", response1.getHeader("Set-Cookie") + "; SameSite=None; Secure=true");
        response1.setHeader("Access-Control-Allow-Origin", request1.getHeader("origin"));
        response1.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response1.setHeader("Access-Control-Max-Age", "3600");
        response1.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response1.setHeader("Access-Control-Allow-Credentials", "true");
        response1.setCharacterEncoding("utf-8");
        chain.doFilter(request1, response1);
    }
}

