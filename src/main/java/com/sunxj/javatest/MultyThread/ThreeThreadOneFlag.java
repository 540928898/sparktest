package com.sunxj.javatest.MultyThread;

import scala.tools.nsc.Global;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreeThreadOneFlag extends Thread implements Runnable {
    @Override
    public void run() {
        System.out.println(1);
    }

    static AtomicInteger t1 = new AtomicInteger(1);
    public static void ThreadOne() {
        while(true) {
            if (t1.get() % 3 == 0) {
                System.out.println(Thread.currentThread().getName()+":"+t1.get());
                t1.getAndIncrement();
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void ThreadTwo() {
        while (true) {
            if (t1.get() % 3 == 1) {
                System.out.println(Thread.currentThread().getName()+":"+t1.get());
                t1.getAndIncrement();
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void ThreadThree() {
        while(true) {
            if (t1.get() % 3 == 2) {
                System.out.println(Thread.currentThread().getName()+":"+t1.get());
                t1.getAndIncrement();
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(ThreeThreadOneFlag::ThreadOne).start();
        new Thread(ThreeThreadOneFlag::ThreadTwo).start();
        new Thread(ThreeThreadOneFlag::ThreadThree).start();
        ThreeThreadOneFlag t1 = new ThreeThreadOneFlag();
        t1.start();
    }
}
