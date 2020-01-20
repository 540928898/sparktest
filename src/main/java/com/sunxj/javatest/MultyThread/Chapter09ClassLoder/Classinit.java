package com.sunxj.javatest.MultyThread.Chapter09ClassLoder;

import java.util.stream.IntStream;

public class Classinit {
    static {
        System.out.println("Static block");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        IntStream.range(0,5).mapToObj(i->new Thread(Classinit::new));
    }
}
