package com.sunxj.javatest.designModel.oneExample;

public class SmartModel {

}
class Sigton{
    private static Sigton signi = new Sigton();
    private Sigton(){};// 私有化构造函数
    public static Sigton getInstance(){
        return signi;
    }
}

enum Singleto{
    INSTANCE;
}