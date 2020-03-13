package com.sunxj.kafka;

import com.sunxj.hase.hbaseConnect.*;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import com.sunxj.hase.hbaseConnect;
public class KafkaToHbase extends KafkaControl {
    static hbaseConnect hb1 = new hbaseConnect();
    public static void setHbaseControl() throws IOException {
        hb1.setMyconnection();
    }
    public static void runConsumer()  {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", BROKER_LIST);//xxx是服务器集群的ip
        properties.put("group.id", Group_ID);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("auto.offset.reset", "latest");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        List<PartitionInfo> partitionInfoList = kafkaConsumer.partitionsFor(TOPIC);
        if(null != partitionInfoList) {
            for(PartitionInfo partitionInfo : partitionInfoList) {
                kafkaConsumer.assign(Collections.singletonList(
                        new TopicPartition(partitionInfo.topic(), partitionInfo.partition())));
            }
        }
        kafkaConsumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s,patition = %s", record.offset(), record.value(),record.partition());
//                System.out.println("Consume Success");
            }
        }
    }
    public static void main(String[] args) throws IOException {

        new Thread(KafkaControl::runProducer).start();
//        System.out.println("Success");

    }

}
