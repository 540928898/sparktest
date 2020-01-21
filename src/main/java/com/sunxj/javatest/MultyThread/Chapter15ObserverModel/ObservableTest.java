package com.sunxj.javatest.MultyThread.Chapter15ObserverModel;

import org.json4s.JsonUtil;

public class ObservableTest {
    public static void main(String[] args) {
        Task task = new Task() {

            @Override
            public Object call() {
                System.out.println("Task 执行了");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
                System.out.println("finished it ");
                return null;
            }
        };
        final TaskLifecycle<String> lifecycle = new TaskLifecycle.EmptyTaskLisfcycle<String >(){
            @Override
            public void onFinish(Thread thread) {
                System.out.println("Thread "+Thread.currentThread().getName()+" is finished");
            }
        };
        Observable observableThread = new ObservableThread(lifecycle,task);
//        Observable observableThread = new ObservableThread<>(()->{
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//
//            }
//            System.out.println("finished it ");
//            return null;
//        });
        observableThread.start();
    }
}
