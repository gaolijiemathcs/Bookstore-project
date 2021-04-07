package com.gao.test;

import com.gao.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

public class JdbcUtilsTest {

    @Test
    public void testJdbcUtils() {
        for(int i = 0; i < 100; i++) {
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);        //获取以后要及时释放 如果没有释放就只能同时连接10个 maxActive=10  如果每次都释放则可以有100次连接 （每次连接完都释放）
        }
    }
}
