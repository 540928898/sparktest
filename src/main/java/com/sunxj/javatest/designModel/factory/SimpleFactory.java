package com.sunxj.javatest.designModel.factory;

import scala.App;

import java.sql.SQLOutput;

public class SimpleFactory {

    public static void main(String[] args) {
        Fruit ne1 = FruitFactorySimple.createFruit("banana");
        Fruit ne2 = FruitFactorySimple.createFruit("apple");
        Fruit ne3 = FruitFactorySimple.createFruit("orange");

        ne1.createFruit();
        ne2.createFruit();
        ne3.createFruit();
    }
}
abstract class Fruit{
    public abstract void createFruit();
}
class Apple extends Fruit{
    @Override
    public void createFruit() {
        System.out.println("get_Apple_case");
    }
}
class Banan extends Fruit{
    @Override
    public void createFruit() {
        System.out.println("Banana_case");
    }
}
class Orange extends Fruit{
    @Override
    public void createFruit() {
        System.out.println("Orange_case");
    }
}

/**
 * 为什么静态 可以调用非静态的成员
 */
class FruitFactorySimple {
    public static Fruit createFruit(String args){
        switch (args){
            case "apple":
                return new Apple();
            case "orange":
                return new Orange();
            case "banana":
                return new Banan();
            default:
                return  null;
        }

    }
}