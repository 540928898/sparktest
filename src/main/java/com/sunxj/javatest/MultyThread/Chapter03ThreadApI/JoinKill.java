package com.sunxj.javatest.MultyThread.Chapter03ThreadApI;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class JoinKill {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = IntStream.range(0,2)
                .mapToObj(JoinKill::createThread)
                .collect(Collectors.toList());
        threads.forEach(Thread::start);
        for (Thread t1:threads
             ) {
            t1.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"#"+i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private static Thread createThread(int index){
        return new Thread(()->{
            for (int i = 0; i < 1011 ; i++) {
                System.out.println(Thread.currentThread().getName()+"#"+i);
                try {
                    TimeUnit.SECONDS.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },String.valueOf(index));
    }
}
