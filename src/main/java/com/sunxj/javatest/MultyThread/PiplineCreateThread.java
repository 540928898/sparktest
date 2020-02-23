package com.sunxj.javatest.MultyThread;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PiplineCreateThread implements Handler {

    @Override
    public List<Thread> createThread(int num) {
        List<Thread> t1 = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            t1.add(new Thread(()->{
                Handler();
            }));
        }
        return t1;
    }
    @Override
    public void Handler() {
        System.out.println("This is Me Father!!");
    }
}
