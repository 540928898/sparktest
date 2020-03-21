package com.sunxj.CreditPro.ClientProducer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
public class main {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "1");
        props.put("retries", 3);
        props.put("batch.size", 16384); // 16K
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432); // 32M
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        int i= 0;
        while(true) {
            i++;
            // 创建 ProducerRecord 可以指定 topic、partition、key、value，其中 partition 和 key 是可选的
            // ProducerRecord<String, String> record = new ProducerRecord<>("dev3-yangyunhe-topic001", 0, "key", line);
            // ProducerRecord<String, String> record = new ProducerRecord<>("dev3-yangyunhe-topic001", "key", line);
            ProducerRecord<String, String> record = new ProducerRecord<>("test", i+"");
            System.out.println("pub success");
            // 只管发送消息，不管是否发送成功
            producer.send(record);
            Thread.sleep(1000);
        }
    }
}