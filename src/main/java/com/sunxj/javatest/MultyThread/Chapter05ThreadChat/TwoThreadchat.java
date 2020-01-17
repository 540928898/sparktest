package com.sunxj.javatest.MultyThread.Chapter05ThreadChat;

public class TwoThreadchat {
    /**
     * 出现IllegalMonitorStateExcetion 是因为 wait和notify没有使用本来的monitor
     * 同时如果int = 1 加了以后就会出现错误，因为内部发生了变化。
     */
    public final static int[] x = new int[]{1} ;
    public void jishu() throws InterruptedException {
        synchronized (x) {
                System.out.println(Thread.currentThread().getName()+" : "+x[0]);
                x[0] =x[0]+ 1;
                this.x.notify();
                this.x.wait();
            }
        }


    public void oushu() throws InterruptedException {
        synchronized (x) {
                System.out.println(Thread.currentThread().getName()+" : "+x[0]);
                x[0] =x[0]+ 1;
                this.x.notify();
                this.x.wait();
            }
        }
    public static void main(String[] args) throws InterruptedException {
        TwoThreadchat t1 = new TwoThreadchat();
        new Thread(()->{
            try {
                for (; ; ) {
                    t1.jishu();
//                    Thread.sleep(1000);
                    if(t1.x[0] >=100){
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                for (; ; ) {
                    t1.oushu();
//                    Thread.sleep(1000);
                    if(t1.x[0] >=1000){
                        break;
                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
