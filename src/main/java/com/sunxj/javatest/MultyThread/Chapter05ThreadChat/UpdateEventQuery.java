package com.sunxj.javatest.MultyThread.Chapter05ThreadChat;

import scala.Int;
import sun.tools.jconsole.JConsole;

import java.util.LinkedList;

public class UpdateEventQuery {
    /**
     * 多个线程会出现的问题：
     * 1. 出现队列已经满了但是还是往里面加
     * 2. 没有元素了还在取
     *
     * 将if 转变为while
     *
     * notify 转为notifyAll
     */
    private final int max;
    static class Event{}
    private static LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;
    public UpdateEventQuery(int max) {
        this.max = max;
    }

    public void offer(Event event) throws InterruptedException {
        synchronized (eventQueue) {
            while(eventQueue.size() >=max){
                try {
                    System.out.println(Thread.currentThread().getName()+"the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"New Submission is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() throws InterruptedException {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                System.out.println(Thread.currentThread().getName()+"No event to take");
                eventQueue.wait();
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            System.out.println(Thread.currentThread().getName()+"I have take an event: "+event+" is handled");
            return event;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final UpdateEventQuery eventQuery = new UpdateEventQuery(10);
        new Thread(()->{
            for (; ; ) {
                try {
                    eventQuery.offer(new Event());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"producer").start();
        Thread.sleep(10000);
        new Thread(()->{
            for (; ; ) {
                try {
                    eventQuery.take();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"consumer").start();
    }

}
