package com.sunxj.javatest.MultyThread.Chapter07ThreadHook;


public class RuntimeHook {
    /**
     * 实现的是在线程中没有活跃的非守护线程时启动hook线程。
     * @param args
     */
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+": HookThread 1 is running");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("HookTHread 1 is exit");
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("HookThread2 is running");

        }));
    }
}
