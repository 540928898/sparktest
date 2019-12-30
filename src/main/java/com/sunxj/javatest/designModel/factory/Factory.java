package com.sunxj.javatest.designModel.factory;

public class Factory {
    public static void main(String[] args) {
       RedaiFactory n1 = new RedaiFactoryManu();

       n1.createApple().createFrurit();
       n1.createBanana().createFrurit();
    }
}

/**
 * 水果 -》 热带水果
 *          -> 香蕉
 *          -> 苹果
 *     -》 寒带水果
 *          -> 橘子
 *          ->梨
 */

abstract class  FruitAll{
    abstract void createFrurit();
}

abstract class RedaiFruit extends FruitAll{
    @Override
   abstract void createFrurit();
}

abstract class HandaiFrurit extends  FruitAll{
    @Override
    abstract void createFrurit();
}

/**
 * 两类寒带和热带水果类
 */
class RedaiManuBanana extends  RedaiFruit{
    @Override
    void createFrurit() {
        System.out.println("Banana is created");
    }
}
class RedaiMAnuPingguo extends RedaiFruit{
    @Override
    void createFrurit() {
        System.out.println("Apple is created");
    }
}

class  HandaiManuOrange extends HandaiFrurit{
    @Override
    void createFrurit() {
        System.out.println("Orange is created");
    }
}
class HandaiManuLi extends  HandaiFrurit{
    @Override
    void createFrurit() {
        System.out.println("Li is craeted");
    }
}


/**
 *
 * 工厂类
 *     热带工厂
 *
 *     寒带工厂
 */
abstract  class FactoryFrurit{
    abstract FruitAll createFrurit();
}

abstract class RedaiFactory {
    abstract FruitAll createBanana();
    abstract FruitAll createApple();
}
abstract class HandaiFactory {
    abstract FruitAll createOrange();
    abstract FruitAll createLi();
}

class RedaiFactoryManu extends RedaiFactory{
    @Override
    FruitAll createBanana() {
       return new RedaiManuBanana();
    }

    @Override
    FruitAll createApple() {
       return  new RedaiMAnuPingguo();
    }
}

class  HandaiFactoryManu extends HandaiFactory{
    @Override
    FruitAll createOrange() {
       return new HandaiManuOrange();
    }

    @Override
    FruitAll createLi() {
        return new HandaiManuLi();
    }
}


















