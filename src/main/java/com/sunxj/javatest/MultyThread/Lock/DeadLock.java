package com.sunxj.javatest.MultyThread.Lock;

public class DeadLock {
    private static String a = "1";

    private static String b = "2";
    public static void methodA(){
        synchronized (a){
            System.out.println("我获得了A锁");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b){
                System.out.println("我是A中的B锁");
            }
        }
    }
    public static void methodB(){
        synchronized (b){
            System.out.println("我是B方法获得了锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("我是B中的A");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->methodA()).start();
        new Thread(DeadLock::methodB).start();
    }

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

}
