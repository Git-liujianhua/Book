package com.atliu.test;

import com.atliu.pojo.Cart;
import com.atliu.pojo.CartItem;
import com.atliu.service.OrderService;
import com.atliu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void creatOrder() {
        OrderService orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门到放弃",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
        System.out.println(orderService.creatOrder(cart, 1));
    }
}