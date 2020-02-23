package com.sunxj.javatest.JVM;

import org.openjdk.jol.info.ClassLayout;

//import java.lang.ref.WeakReference;

public class JVMTest {

    public static void main(String[] args) {

        Object o = new Object();
        String s = ClassLayout.parseInstance(o).toPrintable();
        System.out.println(s);
        System.out.println(Thread.currentThread().getId());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

//    WeakReference weak1 = new WeakReference();
}
