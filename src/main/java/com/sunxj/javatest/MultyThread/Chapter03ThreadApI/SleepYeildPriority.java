package com.sunxj.javatest.MultyThread.Chapter03ThreadApI;

import java.util.stream.IntStream;

public class SleepYeildPriority {
    /**
     * yield  是回到Runnable 状态 也就是说可能马上重新执行
     * sleep是阻塞
     * sleep 可以捕获中断，yield不行
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Sleep");
        new Thread(()->{
            long startTime = System.currentTimeMillis();
            sleep(1_00L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend Time %d",endTime-startTime));
        }).start();
        long startTime = System.currentTimeMillis();
        sleep(3_00L);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Long thread total spend %d",endTime-startTime));
        System.out.println("yield");
        IntStream.range(0,5).mapToObj(SleepYeildPriority::create).forEach(Thread::start);
    }

    /**
     * 批量创建线程
     * @param index
     * @return
     */

    private static Thread create(int index) {
        return new Thread(()->{
            System.out.println(index);
        });
    }
    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
