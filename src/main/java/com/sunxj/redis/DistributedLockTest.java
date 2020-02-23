package com.sunxj.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sun.awt.windows.ThemeReader;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class DistributedLockTest {
    /**
     * 使用线程池 不能讲return_borrow 的  return
     */
    private static int t1 = 0;
    static final  String lock_key ="redis_lock";
//    static
    DistributeLock d11 = new DistributeLock();

    public  List<Thread> createThread(){
        ArrayList<Thread> a1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            a1.add(new Thread(()->{
                for(;;) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (d11.lock(lock_key,Thread.currentThread().getName())){
                        t1 += 1;
                        System.out.println(Thread.currentThread().getName()+" have get Lock and Add 1: Result is "+t1);
                        if (d11.releaseLock(lock_key, Thread.currentThread().getName())){
                            System.out.println(Thread.currentThread().getName()+"Rease Success");
                        }else{
                            System.out.println(Thread.currentThread().getName()+"Realse Fail");
                        }
                    }
                    else {
                        try {
                            System.out.println(Thread.currentThread().getName()+"LockFail");
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }}));
        }
        return  a1;
    }

    public static void main(String[] args) {
        DistributedLockTest d1 = new DistributedLockTest();
        ArrayList<Thread> list1 = (ArrayList<Thread>) d1.createThread();
        list1.forEach(Thread::start);
//        DistributeLock tt = new DistributeLock();

    }
}
