package com.sunxj.javatest.MultyThread.Chapter14Singleton;


public class Holder {
    private static class singHolder{
        private static final Holder t1 = new Holder();
    }
    private Holder(){};
    public static Holder getHolder(){
        return  singHolder.t1;
    }
}
