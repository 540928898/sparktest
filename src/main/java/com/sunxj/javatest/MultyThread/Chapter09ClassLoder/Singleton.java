package com.sunxj.javatest.MultyThread.Chapter09ClassLoder;

import org.apache.commons.math3.analysis.function.Sin;

public class Singleton {
    private static Singleton singleton = new Singleton();//这句话放在x,y的下面结果会不同。仔细思考原因
    private static int x =0;
    private static  int y;
    private Singleton(){
        x++;
        y++;
    }
    public Singleton getSingleton() {
        return singleton;
    }

    public static void main(String[] args) {
        Singleton singleton1 = new Singleton();
        System.out.println(singleton1.x);
        System.out.println(singleton1.y);
    }
}
