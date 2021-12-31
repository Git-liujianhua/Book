package com.atliu.web;


import com.atliu.pojo.User;
import com.atliu.service.UserService;
import com.atliu.service.impl.UserServiceImpl;
import com.atliu.utils.WebUtils;
import com.google.gson.Gson;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            // System.out.println("登录成功" + login);
            //保存用户登录的信息到session域中
            req.getSession().setAttribute("user",login);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、销毁Session中用户登录的信息（或者销毁Session）
        req.getSession().invalidate();
        // 2、重定向到首页（或登录页面）
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        //获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        // Map<String, String[]> parameterMap = req.getParameterMap();
        // for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
        //     System.out.println(entry.getKey() + "=" + Arrays.asList(entry.getValue()));
        // }
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //检查验证码是否正确
        if (token != null && token.equalsIgnoreCase(code)){
            if (userService.existUsername(username)){
                System.out.println("用户名【"+ username +"】已存在");
                //把错误的信息和回显表单项的信息保存到request域中
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else {
                userService.registUser(new User(null,username,password,email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }
        }else {
            //把错误的信息和回显表单项的信息保存到request域中
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码【"+ code +"】不可用");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数UserName
        String username = req.getParameter("username");
        //调用userService.existUsername(username)
        boolean existUsername = userService.existUsername(username);
        //把返回的结果封装成Map对象
        Map<String ,Object> resultMap = new HashMap<>();
        resultMap.put("existUsername",existUsername);
        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);

    }

}
