package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

public class HelloWorld {
    static{
        System.out.println("Hello World Class is Initialized");
    }
    public String welcome(){
        return "HelloWorld";
    }
}
