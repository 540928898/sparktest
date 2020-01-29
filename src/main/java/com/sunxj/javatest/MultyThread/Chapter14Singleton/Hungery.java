package com.sunxj.javatest.MultyThread.Chapter14Singleton;

public class Hungery {
    private static Hungery hungerySingleton = new Hungery();
    private Hungery(){}
    public Hungery getHungerySingleton() {
        return hungerySingleton;
    }

    public static void main(String[] args) {
        Hungery t1 = new Hungery();
        Hungery t2 = new Hungery();
        System.out.println(t1 == t2);
    }

}
