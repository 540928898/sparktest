package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

public class BootstrapClassLoader {
    public static void main(String[] args) {
        System.out.println("Bootstrap:"+String.class.getClassLoader());
        System.out.println(System.getProperty("sun.boot.class.path"));
    }
}
