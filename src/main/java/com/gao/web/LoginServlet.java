package com.gao.web;

import com.gao.pojo.User;
import com.gao.service.UserService;
import com.gao.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //调用userService.login()登录处理业务
        User loginUser = userService.login(new User(null, username, password, null));

        //如果为null则说明登录失败
        if(loginUser == null) {
            // 登录失败
            req.setAttribute("msg", "用户名密码错误");
            req.setAttribute("username", username);
            // 跳回登录页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        } else {
            //登录成功
            //跳转到页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }
}
