package com.sunxj.javatest.MultyThread.Chapter14Singleton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SingtonTest {
    public static ArrayList<Thread> createThread(){
        List t1 = new ArrayList<Thread>();
        for (int i = 0; i < 100; i++) {
            t1.add(new Thread(SingtonTest::runMethod));
        }
        return (ArrayList<Thread>) t1;
    }

    public static void runMethod(){
        LazyModel t1 =LazyModel.getLazyModel();
//        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"'s hashcode = "+t1.hashCode());
//        }
    }
    public static void main(String[] args) {
        Hungery t1 = Hungery.getHungerySingleton();
        Hungery t2 = Hungery.getHungerySingleton();
        System.out.println(t1 == t2);
        LazyModel t3 = LazyModel.getLazyModel();
        LazyModel t4 = LazyModel.getLazyModel();
        System.out.println(t3 == t4);
        ENUM t5 = ENUM.INSTANCE;
        ENUM t6 = ENUM.INSTANCE;
        System.out.println(t5 == t6);
//        ArrayList<Thread> ct = createThread();
//        ct.forEach(Thread::start);
        VolitalSingleton t7 = VolitalSingleton.getVolitalSingleton();
        VolitalSingleton t8 = VolitalSingleton.getVolitalSingleton();
        System.out.println(t7 == t8);
        //多线程检验

//        Thread tm = new Thread(new SingtonTest());
//        Thread tm2 = new Thread(new SingtonTest());
//        tm.start();
//        tm2.start();

    }


}
