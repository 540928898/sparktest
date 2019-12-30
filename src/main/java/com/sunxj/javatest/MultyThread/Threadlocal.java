package com.sunxj.javatest.MultyThread;

public class Threadlocal {
    private static ThreadLocal<Integer> threadlocal1 = new ThreadLocal<Integer>(){
        public Integer initialValue() {
            return 0;
        }
    };
    public int getNextNum() {
        threadlocal1.set(threadlocal1.get() + 1);
        return threadlocal1.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Threadlocal nn = new Threadlocal();
        NumClient nn1 = new NumClient(nn);
        Thread nn2 = new Thread(nn1);
        nn1.start();
        nn2.start();
        nn1.join();
        nn2.join();
        Thread.sleep(1000);
        new Thread(new NumClient(nn)).start();
        new Thread((new NumClient(nn))).start();
    }
    private static class NumClient extends Thread {
        private Threadlocal i;
        private NumClient(Threadlocal enum1){
            this.i = enum1;
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
            for (int j = 0; j < 3; j++) {
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn["
                        + i.getNextNum() + "]");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
