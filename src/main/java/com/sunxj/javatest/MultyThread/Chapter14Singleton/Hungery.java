package com.sunxj.javatest.MultyThread.Chapter14Singleton;

public class Hungery {
    private static Hungery hungerySingleton = new Hungery();
    //构造函数私有化 防止被实例化
    private Hungery(){}
    public static Hungery getHungerySingleton() {
        return hungerySingleton;
    }

}
