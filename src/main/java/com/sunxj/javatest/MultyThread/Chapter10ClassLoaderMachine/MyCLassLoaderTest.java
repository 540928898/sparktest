package com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyCLassLoaderTest {
    /**
     * 自定义类加载器
     * @param args
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        MyClassLoader classLoader = new MyClassLoader("/tmp/classloader1",null);
//        MyClassLoader classLoader = new MyClassLoader();
//        Class<?> aCLass = classLoader.loadClass("com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine.HelloWorld");
//        System.out.println(aCLass.getClassLoader());
//        Object helloWorld = aCLass.newInstance();
//        System.out.println(helloWorld);
//        Method welcomeMethod = aCLass.getMethod("welcome");
//        String result = (String) welcomeMethod.invoke(helloWorld);
        /**
         * 如何绕过系统类加载器呢？
          */
        // 直接跳过app类加载器
        ClassLoader extClassLoader = MyCLassLoaderTest.class.getClassLoader().getParent();
        MyClassLoader classLoader = new MyClassLoader("/tmp/classloader1", extClassLoader);
        Class<?> aCLass = classLoader.loadClass("com.sunxj.javatest.MultyThread.Chapter10ClassLoaderMachine.HelloWorld");
        System.out.println(aCLass.getClassLoader());
        //第二种是通过构造函数时就将父加载器设置为null
    }
}
