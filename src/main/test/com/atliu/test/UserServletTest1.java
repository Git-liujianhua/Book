package com.atliu.test;

import org.junit.Test;

import java.lang.reflect.Method;

public class UserServletTest1 {
    public void login(){
        System.out.println("调用login方法");
    }

    public void resist(){
        System.out.println("调用login方法");
    }

    public void updateUser(){
        System.out.println("调用login方法");
    }


    @Test
    public void userservlet(){
        String action = "login";

        try {
            Method method = UserServletTest1.class.getDeclaredMethod(action);
            System.out.println(method);
            method.invoke(new UserServletTest1());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
