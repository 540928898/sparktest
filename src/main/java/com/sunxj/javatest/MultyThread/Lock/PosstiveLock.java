package com.sunxj.javatest.MultyThread.Lock;

import java.util.Objects;
import java.util.concurrent.atomic.*;
public class PosstiveLock {
//    private static Class<User> user;
    private static AtomicInteger automaticInt = new AtomicInteger(10);
    public static AtomicReference<User> reference = new AtomicReference();
    private static AtomicIntegerFieldUpdater<User> updater = AtomicIntegerFieldUpdater.newUpdater(User.class,"age");

    public static void main(String[] args) {
        System.out.println("原子更新基本类型");
        System.out.println(automaticInt.getAndIncrement());
        System.out.println(automaticInt.get());
//        System.out.println(automaticInt.getAndSet(5));
//        System.out.println(automaticInt.get());
        System.out.println("原子数组修改");
        int[] value1 = new int[] {1,2,3};
        AtomicIntegerArray atomarray1 = new AtomicIntegerArray(value1);
        System.out.println(atomarray1.incrementAndGet(2));
        System.out.println(atomarray1.compareAndSet(2,4,6));
//        //原子修改引用
        System.out.println("原子修改引用");
        User user1 = new User();
        reference.set(user1);
        User updateUser = new User("ketty",18);
        reference.compareAndSet(user1,updateUser);
        System.out.println(user1.name);
        System.out.println(user1.age);
        System.out.println(reference.get().getName());//貌似修改equals没有什么用
//        System.out.println(user1.age);;
//        System.out.println(user1.name);
        // 原子更新属性
//        User user1 = new User();
        System.out.println("原子更新属性");
        System.out.println(user1.age);
        updater.incrementAndGet(user1);
        System.out.println(updater.get(user1));
        System.out.println(user1.age);
    }

    static class User {
        public String name;
        public volatile int age;

        @Override
        public int hashCode() {
            return Objects.hashCode(age);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;//地址相等
            }

            if (obj == null) {
                return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
            }
            if (obj instanceof User) {
                User other = (User) obj;
                if (this.age == other.age) {
                    return true;
                }
            }
            return false;
        }

        public User() {
            this.name = "gupeng";
            this.age = 18;
        }

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public  int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

}
