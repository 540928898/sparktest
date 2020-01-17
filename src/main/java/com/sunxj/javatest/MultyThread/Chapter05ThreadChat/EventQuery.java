package com.sunxj.javatest.MultyThread.Chapter05ThreadChat;



import java.util.LinkedList;

public class EventQuery {
    private final int max;
    static class Event{}
    private static LinkedList<Event> eventQueue = new LinkedList<>();
    private final static int DEFAULT_MAX_EVENT = 10;
    public EventQuery(int max) {
        this.max = max;
    }

    public void offer(Event event) throws InterruptedException {
        synchronized (eventQueue) {
            if(eventQueue.size() >=max){
                try {
                    System.out.println(Thread.currentThread().getName()+"the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+"New Submission is submitted");
            eventQueue.addLast(event);
            eventQueue.notify();
        }
    }
    public Event take() throws InterruptedException {
        synchronized (eventQueue) {
            if (eventQueue.isEmpty()) {
                System.out.println(Thread.currentThread().getName()+"No event to take");
                eventQueue.wait();
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notify();
            System.out.println(Thread.currentThread().getName()+"I have take an event: "+event+" is handled");
            return event;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final EventQuery eventQuery = new EventQuery(10);
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
