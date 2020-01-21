package com.sunxj.javatest.MultyThread.Chapter15ObserverModel;
@FunctionalInterface
public interface Task<T> {
    T call();
}
