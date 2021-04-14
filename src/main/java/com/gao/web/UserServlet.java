package com.gao.web;

import com.gao.pojo.User;
import com.gao.service.UserService;
import com.gao.service.impl.UserServiceImpl;
import com.gao.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    /**
     * 处理登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            //保存用户登录的信息到Session域
            req.getSession().setAttribute("user", loginUser);
            //跳转到页面login_success.html
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    /**
     * 处理注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取Session中的验证码（当次的session保持 故同一个会话）
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除session中的验证码（保证了不会重新获取到 因为获取了一次以后就被remove了）
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        //1 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");


//        User user = new User();
//        Map<String, String[]> parameterMap = req.getParameterMap();
//        for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
//            System.out.println(entry.getKey() + " = " + Arrays.asList(entry.getValue()));
//        }
//        WebUtils.copyParamToBean(req.getParameterMap(), user);

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());

        //2 检查验证码是否正确  === 写死 要求验证码为abcde
        if (token != null && token.equalsIgnoreCase(code)) {
            //正确
            //3 检查 用户名是否可用
            if (userService.existsUsername(username)) {
                // 用户名已存在 不可再注册 不可用 ==>跳回注册页面
                System.out.println("用户名[" + username + "]已存在");

                //把回显信息 保存到request域中
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);

                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                // 可用 调用Service保存到数据库
                userService.registUser(new User(null, username, password, email));
                // 跳到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //把回显信息 保存到request域中国
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);

            //不正确 跳回注册页面
            System.out.println("验证码错误[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
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
//        1、销毁Session中用户登录的信息（或者销毁Session)
        req.getSession().invalidate();
//        2、重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
}
