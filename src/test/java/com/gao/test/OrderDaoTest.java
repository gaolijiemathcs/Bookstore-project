package com.gao.test;

import com.gao.dao.OrderDao;
import com.gao.dao.impl.OrderDaoImpl;
import com.gao.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder(){
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.saveOrder(new Order("123456", new Date(), new BigDecimal(100), 0, 1));
    }
}