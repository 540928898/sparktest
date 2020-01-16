package com.sunxj.javatest.MultyThread.Chapter04SafeAndSyn;

import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.stream.IntStream;

public class SynProblem {
    //Syncronized 对象不能为空
    static class NotNullProblem{
        private static final Object o1 = new Object();
        // 锁的对象不能使用空，一个monitor一个对象，如果对象为空，那么monitor就无从谈起了
//        private static final Object o1 = null;
        public static void getSyn(){
            synchronized (o1) {
                System.out.println("o1 is Sync");
            }
        }
        public static void main(String[] args) {
            System.out.println("My name is getSyn");
            getSyn();
        }
    }
    static class DiffMonitor extends Thread {
        //不同的monitor企图锁住同一个对象 会发现根本没有办法锁住，应为每次创建一个新的线程，都会导致
        // 一个新的montor
        private static final Object o2 = new Object();

        public static void main(String[] args) {
//            IntStream.range(0,5).mapToObj(DiffMonitor::create).forEach(Thread::start);
            for (int i = 0; i < 6; i++) {
               new Thread(new DiffMonitor()).start();
            }

        }
        public static Thread create(int index) {
            return  new Thread(()->{
                synchronized (o2) {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(Thread.currentThread().getName()+": "+i);
                    }
                }
            });
        }
        @Override
        public void run() {
            //synchronized (o2) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName()+": "+i);
                }
            //}
        }
    }
    static class DeadLock{
       //死锁
        static final Object mutex1 = new Object();
        static  final Object mutex2 = new Object();
        public static void read() throws InterruptedException {
            synchronized (mutex1) {
                System.out.println(Thread.currentThread().getName()+": Get mutex1");
                Thread.sleep(100);
                synchronized (mutex2) {
                    for (int i = 0; i < 2; i++) {
                        System.out.println("this is read");
                    }
                }
            }
        }
        public static void write(){
            synchronized (mutex2) {
                System.out.println(Thread.currentThread().getName()+": Get mutex2");
                synchronized (mutex1) {
                    for (int i = 0; i < 3; i++) {
                        System.out.println("This is write");
                    }
                }
            }
        }

        public static void main(String[] args) {
            new Thread(()->{
                try {
                    read();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                write();
            }).start();
        }
    }
}
