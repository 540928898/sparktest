package com.sunxj.javatest.MultyThread.Chapter15ObserverModel;

import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;

public class ObservableThread <T> extends Thread implements Observable {


    /**
     * Run函数相当于启动一个线程。
     * 同时update函数起到了一个改变观测值得行为，由run函数调用。
     * 真正执行业务逻辑的是在Task函数里面
     */

    private final TaskLifecycle<T> lifecycle;
    private final Task<T> task;
    private Cycle cycle;
    public ObservableThread(Task<T> task) {
        //一个emptyTaskLisfcycle的实现
        this(new TaskLifecycle.EmptyTaskLisfcycle<>(), task);
    }
    public ObservableThread(TaskLifecycle<T> lifecycle, Task<T> task) {
        super();
        if (task == null) {
            throw new IllegalArgumentException("Ths task is required");
        }
        this.lifecycle = lifecycle;
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println("Run函数执行了");
        this.update(Cycle.STARTED, null, null);
        try {
            this.update(Cycle.RUNNING, null, null);
            T result = this.task.call();
            this.update(Cycle.DONE, result, null);
            System.out.println("run函数结束了");
        } catch (Exception e) {
            this.update(Cycle.ERROR, null, e);
        }
    }

    private void update(Cycle cycle, T result, Exception e) {
        this.cycle = cycle;
        if (lifecycle == null) {
            return;
        }
        try {
            switch (cycle) {
                case STARTED:
                    this.lifecycle.onStart(currentThread());
                    break;
                case RUNNING:
                    this.lifecycle.onRunning(currentThread());
                    break;
                case DONE:
                    this.lifecycle.onFinish(currentThread());
                    break;
                case ERROR:
                    this.lifecycle.onError(currentThread());
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            if (cycle == Cycle.ERROR) {
                throw ex;
            }
        }

    }
    @Override
    public Cycle getCycle() {
        return this.cycle;
    }
}
