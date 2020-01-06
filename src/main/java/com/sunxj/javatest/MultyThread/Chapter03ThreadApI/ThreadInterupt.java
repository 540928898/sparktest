package com.sunxj.javatest.MultyThread.Chapter03ThreadApI;

import java.util.concurrent.TimeUnit;

public class ThreadInterupt {
    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);

            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("Oh,I am interrupt");
            }
        });
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread1.interrupt();
    }
}
