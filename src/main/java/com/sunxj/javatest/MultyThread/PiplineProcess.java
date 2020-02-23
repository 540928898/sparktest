package com.sunxj.javatest.MultyThread;

import java.util.ArrayList;
import java.util.List;

public class PiplineProcess extends PiplineCreateThread implements Handler{

    @Override
    public void Handler() {
        System.out.println("This is Me Sun!!");
    }
    public static void main(String[] args) {
        PiplineProcess t1 = new PiplineProcess();

    }
}
