package com.atliu.pojo;

import java.math.BigDecimal;
import java.util.Date;


public class Order {

    private String OrderId;
    private String createTime;
    private BigDecimal price;
    //0未发货，1已发货，2已签收
    private Integer status = 0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, String createTime, BigDecimal price, Integer status, Integer userId) {
        OrderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId='" + OrderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
