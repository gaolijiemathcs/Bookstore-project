package com.gao.service;

import com.gao.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart, Integer userId);
}
