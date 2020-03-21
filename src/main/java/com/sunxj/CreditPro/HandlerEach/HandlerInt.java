package com.sunxj.CreditPro.HandlerEach;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface HandlerInt {
    public void DoHandle(ConsumerRecord<String, String> consumerRecord);
}
