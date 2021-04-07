package com.gao.web;

import java.lang.reflect.Method;

public class UserServletTest {
    public void login() {
        System.out.println("这是logiin()方法调用");
    }

    public void regist() {
        System.out.println("这是regist()方法调用");
    }

    public void updateUser() {
        System.out.println("这是updateUser()方法调用");
    }

    public void updateUserPassword() {
        System.out.println("这是updateUserPassword()方法调用");
    }

    public static void main(String[] args) {
        //获取action 业务鉴别字符串 获取响应的业务 方法反射对象
        String action = "updateUser";
        try {
            Method method = UserServletTest.class.getDeclaredMethod(action);
//            System.out.println(method);
            method.invoke(new UserServletTest() );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
