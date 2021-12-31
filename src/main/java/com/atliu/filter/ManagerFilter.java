package com.atliu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        Object user = httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            //如果等于null则跳转回登录页面，说明还没有登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            //如果登录成功则允许该程序继续访问用户的目标资源
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
