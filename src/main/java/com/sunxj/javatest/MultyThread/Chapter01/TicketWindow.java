package com.sunxj.javatest.MultyThread.Chapter01;

public class TicketWindow extends Thread  {
    private final String name;
    private static final int Max = 50;
    private static int index = 1;
    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= Max){
            System.out.println("柜台： "+name+"当前叫号是"+(index++));
        }
    }

    public static void main(String[] args) {
        TicketWindow t1 = new TicketWindow("1号");
        TicketWindow t2 = new TicketWindow(("2号"));
       t1.start();
       t2.start();
    }
}
