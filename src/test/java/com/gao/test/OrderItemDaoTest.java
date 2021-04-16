package com.gao.test;

import com.gao.dao.OrderItemDao;
import com.gao.dao.impl.OrderItemImpl;
import com.gao.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDao orderItemDao = new OrderItemImpl();
        orderItemDao.saveOrderItem(new OrderItem(null, "java编程思想", 1, new BigDecimal(100), new BigDecimal(100), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "JavaWeb", 2, new BigDecimal(88), new BigDecimal(100), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "Redis", 3, new BigDecimal(100), new BigDecimal(100), "123456"));
        orderItemDao.saveOrderItem(new OrderItem(null, "TCP/IP", 1, new BigDecimal(100), new BigDecimal(100), "123456"));
    }
}