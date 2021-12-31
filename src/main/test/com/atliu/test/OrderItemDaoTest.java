package com.atliu.test;

import com.atliu.dao.OrderItemDao;
import com.atliu.dao.impl.OrderItemDaoImpl;
import com.atliu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }
}