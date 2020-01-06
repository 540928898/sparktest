package com.sunxj.javatest.MultyThread.Chapter03ThreadApI;

import java.util.concurrent.TimeUnit;


/**
 * 如果使用可中断的方法，那么使用isinterrupt就不能看出效果
 */
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
        Thread thread2 = new Thread(()->{
            while (true) {
                System.out.println(Thread.interrupted());
            }
        });
        // Thread的interrupted的方法。
//        thread2.setDaemon(true);
//        thread2.start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        thread2.interrupt();
//        thread1.start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        thread1.interrupt();
//        System.out.println(thread1.isAlive());
//        System.out.println(thread1.isInterrupted());
//        System.out.println(thread1.interrupted());
        System.out.println("Main Thread is Interruped? "+Thread.interrupted());
        Thread.currentThread().interrupt();
        /**
         * 这个地方非常神奇，如果使用注释的话，那么线程就不会出现中断，睡眠仍会执行，因为interrupted将擦除中断记号
         */
//        System.out.println("Maxin Thread is Interruted?" + Thread.interrupted());
        System.out.println("Maxin Thread is Interruted?" + Thread.currentThread().isInterrupted());

        try {
            System.out.println("执行了Try");
            TimeUnit.MILLISECONDS.sleep(2);
            System.out.println("执行了Sleep");

        }catch (InterruptedException e){
            System.out.println("I will be interruped still");
        }
    }
}
