package com.sunxj.javatest.MultyThread.Chapter03ThreadApI;

import java.util.concurrent.TimeUnit;

public class KillTest {
    /**
     * 1. 捕获中断信号
     * 2. 由于线程的中断标签容易被修改，那么使用volitate 变量来保证flag开关
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work");
                while (!isInterrupted()) {
                    System.out.println(Thread.currentThread().getName()+"#"+"is working");

                }
            }
        };
       t1.start();
       TimeUnit.SECONDS.sleep(1);
        System.out.println("System will shut down");
        t1.interrupt();
    }
}
