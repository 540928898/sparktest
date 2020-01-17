package com.sunxj.javatest.MultyThread.Chapter06ThreadGroup;

public class OperationGroup {
    public static void main(String[] args) {
        ThreadGroup tg1 = Thread.currentThread().getThreadGroup();
        ThreadGroup tg2 = new ThreadGroup("tg2");
        System.out.println(tg2.getParent() == tg1);
        ThreadGroup tg3 = new ThreadGroup(tg2, "tg3");
        System.out.println(tg3.getParent() == tg1);
        new Thread(tg3,()->{
            System.out.println("my name is tg3group : "+Thread.currentThread().getThreadGroup());
        },"tg3groupTHREAD1").start();
    }
}
