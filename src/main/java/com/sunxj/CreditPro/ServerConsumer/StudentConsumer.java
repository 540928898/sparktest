package com.sunxj.CreditPro.ServerConsumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.xsom.impl.scd.Iterators;
import com.sunxj.kafka.ConnectKafka;
import com.sunxj.redis.JedisPool;
import com.sunxj.redis.RedisTool;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.protocol.types.Field;
import org.codehaus.jackson.map.ObjectMapper;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.Duration;
import java.util.*;

public class StudentConsumer{
    public final static String Group_ID = "StudentGetJobGroupConsumer";
    public final static String TOPIC = "StudentGetJob";
    public final static int BUFFER_SIZE = 64 * 1024;
    public final static int TIMEOUT = 20000;
    public final static int INTERVAL = 10000;
    public final static String BROKER_LIST = "localhost:9092,localhost:9093,localhost:9094";
    public final static int GET_MEG_INTERVAL = 1000;
    static String[] compoonyList = new String[]{
            "Huawei","Xiaomi","Tengxun","Toutiao","Meituan","Douying"
    };
    public static ArrayList<String>  fourCompony(){
        ArrayList<String> t1 = new ArrayList<>();
        for (int i = 0; i <4 ; i++) {
            t1.add(compoonyList[new Random().nextInt(6)]);
        }
        return  t1;
    }

    public static void runConsumer() {
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
        System.out.println(partitionInfoList);
        if(null != partitionInfoList) {
            for(PartitionInfo partitionInfo : partitionInfoList) {
                kafkaConsumer.assign(Collections.singletonList(
                        new TopicPartition(partitionInfo.topic(), partitionInfo.partition())));
            }
        }
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s,patition = %s\n", record.offset(), record.value(),record.partition());
                JSONObject value = JSONObject.parseObject(record.value());
                String jsonarr = value.getString("uid");
                System.out.println(JSON.toJSONString(fourCompony()));
                JedisPool.getJedis().set(jsonarr,JSON.toJSONString(fourCompony()));
            }
        }
    }
    public static void main(String[] args) {
        new Thread(StudentConsumer::runConsumer).start();
    }
}
