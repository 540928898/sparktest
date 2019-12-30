package com.sunxj.javatest.designModel.oneExample;

public class LazyModel {
    static LazySingletonStatic s1 = LazySingletonStatic.newInstance();
    static LazySingletonStatic s2 = LazySingletonStatic.newInstance();

    public static void main(String[] args) {
        System.out.println(s1.equals(s2));
    }

}
//线程安全使用Scronized
class LazySingleto{
    private static LazySingleto instance = null;
    private LazySingleto(){}
    public static synchronized LazySingleto getLazySingleto() {
        if (instance == null) {
            instance = new LazySingleto();
        }
            return instance;
    }
}
class LazySingleton2{
    private  static LazySingleton2 instance = null;
    private LazySingleton2(){}
    public static LazySingleton2 getLazySingleton(){
        synchronized (LazySingleton2.class){ //使用反射的原理，获取这个类的锁，判断这个类是否被使用
            if(instance == null){
                instance = new LazySingleton2();
            }
            return instance;
        }

    }
}

/**
 * 每次访问都要进行线程同步（即 调用synchronized锁)，造成过多的同步开销（加锁 = 耗时、耗能）
 * 实际上只需在第1次调用该方法时才需要同步，一旦单例创建成功后，就没必要进行同步
 */

class LazySingleton2New{
    private  static LazySingleton2New instance = null;
    private LazySingleton2New(){}
    public static LazySingleton2New getLazySingleton() {
        if (instance == null) {//先判断是否为空
            synchronized (LazySingleton2New.class) {
                if (instance == null) {
                    instance = new LazySingleton2New();
                }
            }
        }
        return instance;
    }
}

/**
 * 主要是由于指令重排，导致空指针异常，因此可以使用volatile
 */
class LazySingleton2NewVolititle {
    private volatile static LazySingleton2NewVolititle instance = null;

    private LazySingleton2NewVolititle() {
    }

    public static LazySingleton2NewVolititle getLazySingleton() {
        if (instance == null) {//先判断是否为空
            synchronized (LazySingleton2NewVolititle.class) {
                if (instance == null) {
                    instance = new LazySingleton2NewVolititle();
                }
            }
        }
        return instance;
    }
}

/**
 * 使用静态内部类
 * 调用过程说明：
 *       // 1. 外部调用类的newInstance()
 *       // 2. 自动调用Singleton2.ourInstance
 *        // 2.1 此时单例类Singleton2得到初始化
 *        // 2.2 而该类在装载 & 被初始化时，会初始化它的静态域，从而创建单例；
 *        // 2.3 由于是静态域，因此只会JVM只会加载1遍，Java虚拟机保证了线程安全性
 *       // 3. 最终只创建1个单例
 */

class LazySingletonStatic{
    private static class LazySingletonStatic2 {
        private static LazySingletonStatic instance = new LazySingletonStatic();
    }
    private LazySingletonStatic(){}
    public static  LazySingletonStatic newInstance() {
        return LazySingletonStatic2.instance;
    }
}