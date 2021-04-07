package com.gao.test;

import com.gao.pojo.User;
import com.gao.service.UserService;
import com.gao.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "bbj168", "6666", "bbj168@qq.com"));
        userService.registUser(new User(null, "aac168", "6666", "aac168@qq.com"));
    }

    @Test
    public void login() {
//        System.out.println(userService.login(new User(null, "glj1688", "abcsd", null)));  //登录失败 打印null
        System.out.println(userService.login(new User(null, "aac168", "6666", null)));  //登录
    }

    @Test
    public void existsUsername() {
        if(userService.existsUsername("aac168")) {
            System.out.println("用户名已存在！");
        } else {
            System.out.println("用户名可用！");
        }
    }
}