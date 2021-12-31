package com.atliu.service;

import com.atliu.pojo.User;

public interface UserService {
   /**
    * 注册用户
    * @param user
    */
   public void registUser(User user);

   /**
    * 用户登录
    * @param user
    */
   public User login(User user);

   /**
    * 检查用户名是否存在
    * @param username
    * @return
    */
   public boolean existUsername(String username);
}
