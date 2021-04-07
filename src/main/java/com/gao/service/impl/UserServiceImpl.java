package com.gao.service.impl;

import com.gao.dao.UserDao;
import com.gao.dao.impl.UserDaoImpl;
import com.gao.pojo.User;
import com.gao.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if(userDao.queryUserByUsername(username) == null) {
            //等于null 表明没查到 代表可用注册
            return false;
        }
        //存在则代表查到 代表不可被注册
        return true;
    }
}
