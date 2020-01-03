package com.sunxj.javatest.MultyThread.Chapter01;

public class TicketWindowRunnable implements Runnable {
    /**
     * Runnable 和普通的Thread的区别 ：
     * Runnable 可以实现不同线程操作同一个资源，但是普通的Thread 只能创建不同的线程 static、等的用法。
     * 总的来说 也就是 Thread 是负责创建等控制线程的职责，而Runnable 是为了实现Run的功能
     */
    private static String name;
    private static int Max = 50;
    private int index = 1;
    public int getnumber(){
        return index++;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (index < Max){
            System.out.println(Thread.currentThread()+" 的号码是： "+(index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TicketWindowRunnable t1 = new TicketWindowRunnable();
        ThreadGroup group1 = new ThreadGroup("groupNotMain");

        Thread windowThread1 = new Thread(t1,"一号");
        Thread windowThread2 = new Thread(group1,t1,"二号");
        Thread windowThread3 = new Thread(t1,"三号");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }

}
