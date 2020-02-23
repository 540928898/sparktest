package com.sunxj.javatest.Reflect;

/**
 * classloader不会加载
 * forName会自动加载静态代码块
 */
public class ClassLoaderAndForName {
    public static void main(String[] args) throws ClassNotFoundException {
        Class s1 = Class.forName("com.sunxj.javatest.Reflect.Foo2");
        System.out.println("###############");
        System.out.println(s1);
        Class s2 = ClassLoader.getSystemClassLoader().loadClass("com.sunxj.javatest.Reflect.Foo2");
        System.out.println(s2);
    }
}


class Foo2{
    static {
        System.out.println("执行了静态代码块");
    }
    //静态变量
    private static String staticFiled = staticMethod();

    //赋值静态变量的静态方法
    public static String staticMethod(){
        System.out.println("执行了静态方法");
        return "给静态字段赋值了";
    }
}