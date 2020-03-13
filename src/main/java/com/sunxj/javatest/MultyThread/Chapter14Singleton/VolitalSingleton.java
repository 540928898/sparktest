package com.sunxj.javatest.MultyThread.Chapter14Singleton;

public class VolitalSingleton {
    private static volatile  VolitalSingleton volitalSingleton;
    private VolitalSingleton(){}
    public static VolitalSingleton getVolitalSingleton(){
        if(volitalSingleton == null){
            synchronized (VolitalSingleton.class){
                if(volitalSingleton == null){
                    volitalSingleton = new VolitalSingleton(); //这句话有问题的 不是原子性 其余变量可能会访问到
                }
            }
        }
        return  volitalSingleton;
//        synchronized (VolitalSingleton.class)
    }
}
