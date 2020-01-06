package com.sunxj.javatest.MultyThread.Chapter02;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *谨慎运行  实现不断创建线程 JVM 虚拟机栈会出现溢出
 */
public class ThreadCounter extends Thread{
    final static AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        try {
            while (true) {
                new ThreadCounter().start();
            }
        } catch (Throwable throwable) {
            System.out.println("Fail at "+counter.get());
        }
    }
    @Override
    public void run() {
       try {
           System.out.println("the+" + counter.getAndIncrement() + " thread is created");
           TimeUnit.MINUTES.sleep(10);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}
