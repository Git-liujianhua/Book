package com.atliu.service.impl;

import com.atliu.dao.UserDao;
import com.atliu.dao.impl.BaseDao;
import com.atliu.dao.impl.UserDaoImpl;
import com.atliu.pojo.User;
import com.atliu.service.UserService;

public class UserServiceImpl extends BaseDao implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existUsername(String username) {
        if (userDao.queryUserByUsername(username) == null){
            //等于null说明没有查到该用户名，则用户名可用
            return false;
        }
        return true;
    }
}
