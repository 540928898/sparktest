package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

public class BrokeDelegateClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        BrokeDelegateClassLoader bb1 = new BrokeDelegateClassLoader();
        Class<?> aCLass = bb1.loadClass("com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine.HelloWorld");
        System.out.println(aCLass.getClassLoader());
    }
}
