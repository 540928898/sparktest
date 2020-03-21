package com.sunxj.CreditPro.HandlerEach;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class StudentAction implements HandlerInt{
    @Override
    public void DoHandle(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("this is StudentAction!");
        System.out.println("--------------- ConsumerRecord ------------------------ ");
        System.out.println("topic:   "+consumerRecord.topic());
        System.out.println("partition:   "+consumerRecord.partition());
        System.out.println("offset:   "+consumerRecord.offset());
        System.out.println("toString:   "+consumerRecord.toString());
    }
    /**
     * 处理学生操作行为，进行计数，送入Redis
     */
}
