package com.sunxj.javatest.MultyThread;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CreateThread extends Thread {
    //继承类实现线程的创建
    static class CreateRawThread extends Thread {
        @Override
        public void run() {
            System.out.println(
                    Thread.currentThread().getName()+":is running!!"
            );
        }
    }
    //通过实现Runnable接口实现线程的创建
    static class CreateRunnableThread implements Runnable{
        @Override
        public void run() {
            try {
                sleep(1000);
                System.out.println("I am awake!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+"RunnableThread is running!!");
        }
    }
    /*
   使用Callable实现线程的创建
    Callable 可以实现异常的抛出，同时可以实现线程返回值
    */

    static class CreateCallableThread implements Callable<String >{

        @Override
        public String  call() throws Exception {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+":"+i);
                sleep((int)Math.random()*100);
            }
            return "1";
        }
    }


    /*
    使用Executor创建多线程，实际上是使用了线程池
     */
    static class CreateThreadExecutive{
        Executor executor1 = Executors.newSingleThreadExecutor();
        public void create(){
            for (int i = 0; i < 10; i++) {
                 executor1.execute(new CreateRunnableThread());
            }
        }
    }
public static String changeType(String element){
    return element + "tt";
}
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("继承Thread的实现方式");
//        CreateRawThread rawThread = new CreateRawThread();
//        rawThread.start();
//
//        System.out.println("实现Runnable接口的实现方式");
//        CreateRunnableThread th2 = new CreateRunnableThread();
//        Thread th = new Thread(th2);
//        th.start();

        System.out.println("使用Callable，Futuretask 实现线程的创建，并获取线程返回值");
        CreateCallableThread my1 = new CreateCallableThread();
//        CreateCallableThread my2 = new CreateCallableThread();
//        CreateCallableThread my3 = new CreateCallableThread();
//        CreateCallableThread my4 = new CreateCallableThread();

        FutureTask<String> task1 = new FutureTask<String>(my1);
//        FutureTask<String> task2 = new FutureTask<String>(my1);
//        FutureTask<String> task3 = new FutureTask<String>(my1);
//        FutureTask<String> task4 = new FutureTask<String>(my1);



        Thread thread1 = new Thread(task1);
//        Thread thread2 = new Thread((task2));
//        Thread thread3 = new Thread(task3);
//        Thread thread4 = new Thread(task4);
        thread1.setName("gupengpeng");//设置进程名字
        thread1.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
        System.out.println(task1.get());
//        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
//        features.forEach(n -> System.out.println(changeType((String) n)));
//        System.out.println("使用Executor实现线程的创建");
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("executor");
//            }
//        });
//        executorService.shutdown();

    }
}
