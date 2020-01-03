package com.sunxj.javatest.MultyThread;

public class TryConcurrency {
    private static void browserNews() throws InterruptedException {
        while (true) {
            System.out.println("I am reading News");
            Thread.sleep(100);
        }
    }
    private static void listenMusic() throws InterruptedException{
        while (true) {
            System.out.println("I am listening Music");
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            try {
                listenMusic();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        browserNews();
    }
}
