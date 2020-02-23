package com.sunxj.javatest.Reflect;

public class ReflectTest {
    public static void main(String[] args) {
        Foo foo1 = new Foo();
        Class C = foo1.getClass();
        System.out.println(C);
        Class C2 = Foo.class;
        System.out.println(C2);
        try{
            Class C3 = Class.forName("com.sunxj.javatest.Reflect.Foo");
            System.out.println(C3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /**
         * classLoader和 forname的区别
         */
    }
}

class Foo{
    void print1(){
        System.out.println("this is Fool");
    }
        }
