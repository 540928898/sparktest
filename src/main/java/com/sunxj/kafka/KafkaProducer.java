//package com.sunxj.kafka;
//
//
//
////
////import org.apache.kafka.clients.producer.KafkaProducer;
////import org.apache.kafka.clients.producer.ProducerRecord;
//import java.util.Properties;
//import java.util.Properties;
//
//public class KafkaProducer {
//
////此处配置的是kafka的端口
//public static void main(String[] args) {
//    String TOPIC="test";
//    Properties props = new Properties();
//    props.put("metadata.broker.list", "localhost:9092");
//    props.put("serializer.class", "kafka.serializer.StringEncoder");
////配置key的序列化类
//    props.put("key.serializer.class", "kafka.serializer.StringEncoder");
//
//    props.put("request.required.acks","-1");
//
//    Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(props));
//
//    char[] messageNo = new char[0];
//    String key = String.valueOf(messageNo);
//    String data = "hello kafka message " + key;
//    producer.send(new KeyedMessage<String, String>(TOPIC, key ,data));
//}
//
//
//}
