package com.sunxj.javatest.MultyThread.Chapter14Singleton;

public class DoubleCheck {
    private volatile static DoubleCheck doubleCheck;
    private DoubleCheck(){}
    public static DoubleCheck getDoubleCheck(){
        if(doubleCheck == null){
            synchronized (DoubleCheck.class){
                if(doubleCheck == null){
                    doubleCheck = new DoubleCheck();
                }
            }
        }
        return doubleCheck;
    }

}
