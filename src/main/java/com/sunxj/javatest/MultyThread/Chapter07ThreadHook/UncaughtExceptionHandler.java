package com.sunxj.javatest.MultyThread.Chapter07ThreadHook;

import java.util.function.DoubleToIntFunction;

public class UncaughtExceptionHandler {
    /**
     * 启动异常捕获的hook线程
      * @param args
     */
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName() + " :  occured exception");
            e.printStackTrace();
        });
        final Thread th1 = new Thread(() -> {
            try {
                Thread.sleep(100);
                System.out.println(1 / 0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Test_thread");
        th1.start();
    }
}
