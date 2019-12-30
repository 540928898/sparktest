package com.sunxj.javatest.MultyThread.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NegtiveLock implements Runnable{
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    private  final  static Object obj1 = new Object();
    static int count = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition =lock.newCondition();
    public static void increase(){
        synchronized (obj1) {
            for (int i = 0; i < 1000; i++) {
                count += 1;
            }
        }
    }
    @Override
    public void run() {
        increase();
    }
    public synchronized void test(){
        System.out.println("test syn");
    }
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(new NegtiveLock());
//        Thread t2 = new Thread(new NegtiveLock());
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//        System.out.println(count);
        System.out.println("Hello World");

        synchronized (obj1) {
            System.out.println("insert Syn...");
        }
    }
}
