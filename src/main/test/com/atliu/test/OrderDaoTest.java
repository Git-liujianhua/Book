package com.atliu.test;

import com.atliu.dao.OrderDao;
import com.atliu.dao.impl.OrderDaoImpl;
import com.atliu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(date));
        // sdf.parse(string);//解析
        orderDao.saveOrder(new Order("1234567891",sdf.format(date),new BigDecimal(100),0,1));

    }
}