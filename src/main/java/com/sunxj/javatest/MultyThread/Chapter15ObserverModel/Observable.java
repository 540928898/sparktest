package com.sunxj.javatest.MultyThread.Chapter15ObserverModel;

public interface Observable {
    /**
     * 这里是写需要观察的变量和对于提取需要的观察函数。
     */
    enum Cycle{
        STARTED,RUNNING,DONE,ERROR
    }
    Cycle getCycle();
    void start();
    void  interrupt();
}
