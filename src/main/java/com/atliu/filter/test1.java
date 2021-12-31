package com.atliu.filter;

public class test1 {

    public void creatOrder(){
        String name = Thread.currentThread().getName();
        System.out.println("test1当前线程["+name+"]中保存的数据是："+ThreadLocalTest.threadLocal.get());
    }
}
