package com.sunxj.javatest.MultyThread.Lock;

public class VolitileTest extends Thread {
    volatile boolean flag = false;
    int i = 0;

    @Override
    public void run() {
        while (!flag) {  //flag是从工作内存中取得，并不是从主内存取得
            i++;
        }
    }

    public static void count1(){
        System.out.println(11+"count");
    }
    public static void main(String[] args) throws InterruptedException {
        VolitileTest th1 = new VolitileTest();
        th1.start();
        Thread.sleep(2000);
        th1.flag = true;
        System.out.println(th1.i);
        VolitileTest aaa = new VolitileTest();
        new Thread(()->count1()).start();
    }
}
