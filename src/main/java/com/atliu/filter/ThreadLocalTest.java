package com.atliu.filter;

import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadLocalTest {

    // public static Map<String ,Object> data = new ConcurrentHashMap<>();
    // public static Map<String ,Object> data = new Hashtable<String ,Object>();
    public static ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
    private static Random random = new Random();

    public static class Task implements Runnable{

        @Override
        public void run() {
            //在run方法中，随机生成一个变量（线程要关联的数据），然后以当前线程名为key保存到map中
            Integer i = random.nextInt(1000);
            //获取当前线程名
            String name = Thread.currentThread().getName();
            System.out.println("线程["+name+"]生成的随机数是：" + i);
            // data.put(name,i);
            threadLocal.set(i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new test1().creatOrder();
            //在run方法结束之前，以当前线程名获取数据并打印，查看是否可以取出操作
            // Object o = data.get(name);
            Object o = threadLocal.get();
            System.out.println("线程["+name+"]快结束时取出关联的数据是：" + o);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3 ;i++){
            new Thread(new Task()).start();
        }
    }
}

