package com.sunxj.kafka;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
public class KafkaControl extends ConnectKafka{
    public static void runConsumer(){
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
        TopicPartition p = new TopicPartition(TOPIC,0);
        List<PartitionInfo> partitionInfoList = kafkaConsumer.partitionsFor(TOPIC);
        kafkaConsumer.assign(Arrays.asList(p));
//        if(null != partitionInfoList) {
//            for(PartitionInfo partitionInfo : partitionInfoList) {
//                kafkaConsumer.assign(Collections.singletonList(
//                        new TopicPartition(partitionInfo.topic(), partitionInfo.partition())));
//            }
//        }
//        kafkaConsumer.subscribe(Arrays.asList(TOPIC));
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s,patition = %s", record.offset(), record.value(),record.partition());
//                System.out.println("Consume Success");
            }
        }
    }

    public static void runProducer() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", BROKER_LIST);
//        kafkaProps.put("zk.connect", "localhost:2181");
        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("partitioner.class","com.sunxj.kafka.PartitionNew");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i += 1;
            producer.send(new ProducerRecord<String, String>(TOPIC, "" + i, "asd"));
            System.out.println("produce success");
        }
    }
    public static void main(String[] args) throws InterruptedException {
       Thread t1 = new Thread(KafkaControl::runProducer) ;
       t1.start();
        new Thread((KafkaControl::runConsumer)).start();
    }
}
