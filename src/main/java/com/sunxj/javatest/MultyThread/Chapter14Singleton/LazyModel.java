package com.sunxj.javatest.MultyThread.Chapter14Singleton;

public class LazyModel {
    private static LazyModel lazyModel;
    private LazyModel(){}
    public  static LazyModel getLazyModel(){
        if (lazyModel ==null){
            lazyModel = new LazyModel();
        }
        return  lazyModel;
    }
}
