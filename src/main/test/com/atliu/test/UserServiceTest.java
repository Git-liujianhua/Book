package com.atliu.test;

import com.atliu.pojo.User;
import com.atliu.service.UserService;
import com.atliu.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"liujianhua","123456","1330517751@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null,"liujianhua","123456",null)));
    }

    @Test
    public void existUsername() {
        if (userService.existUsername("liujianhua")){
            System.out.println("用户名已存在");
        }else {
            System.out.println("用户名可用");
        }
    }
}