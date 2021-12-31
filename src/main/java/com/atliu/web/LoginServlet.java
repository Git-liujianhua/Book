package com.atliu.web;

import com.atliu.pojo.User;
import com.atliu.service.UserService;
import com.atliu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User login = userService.login(new User(null, username, password, null));
        if (login == null){
            System.out.println("登录失败");
            //把错误的信息和回显表单项的信息保存到request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
            System.out.println("登录成功" + login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }
}
