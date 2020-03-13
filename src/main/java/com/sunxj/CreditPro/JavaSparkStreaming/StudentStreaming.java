package com.sunxj.CreditPro.JavaSparkStreaming;
import java.util.*;



import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.RDD;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

public class StudentStreaming {
    public static <U> void main(String[] args) throws InterruptedException {
        /**
         * 第一步：配置SparkConf，
         *     1. 因为 Spark Streaming 应用程序至少有一条线程用于不断的循环结束数据，并且至少有一条线程用于处理
         *              接收的数据（否则的话无线程用于处理数据，随着时间的推移，内存和磁盘都会不堪重负）
         *  2. 对于集群而已，每个 Executor 一般肯定不止一个线程，那对于处理 Spark Streaming应用程序而言，每个 Executor 一般分配多少Core
         *     比较合适？根据我们过去的经验，5个左右的 Core 是最佳的（一个段子分配为基数 Core 表现最佳，）
         */
//        SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("sparkStreaming");
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("sparkStreaming");

        // 窗口间隔
        JavaStreamingContext streamingContext = new JavaStreamingContext(conf, Durations.seconds(5));
        // kafka 参数
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");//多个可用ip可用","隔开
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", "sparkStreaming");
        String[] TopicSet = new String[]{"test","StudentGetJob"};
        Collection<String> topics = Arrays.asList(TopicSet);//配置topic，可以是数组
//        Map<TopicPartition, Long> topicsAndOffset = new HashMap<>();//  配置对应的主题分区的offset，从指定offset消费
//        topicsAndOffset.put(new TopicPartition("test", 0), 0L);
//        topicsAndOffset.put(new TopicPartition("test", 1), 10L);
//        topicsAndOffset.put(new TopicPartition("test", 2), 330L);


//声明 streaming-kafka-Direct 方式拉取数据  分区一一对应
        JavaInputDStream<ConsumerRecord<String, String>> javaInputDStream =KafkaUtils.createDirectStream(
                streamingContext,
                LocationStrategies.PreferConsistent(), //分区策略
                ConsumerStrategies.Subscribe(topics, kafkaParams)//消费者策略   方式一 从每个分区的 0 offset 开始消费
//                ConsumerStrategies.Subscribe( topics,kafkaParams,topicsAndOffset) //消费者策略   方式二 从每个分区的 指定的offset 开始消费
        );

        // 将kafka中的数据拉去出来之后进行 解析获取key和value值（使用print打印）
        JavaPairDStream<String, String> javaPairDStream = javaInputDStream.mapToPair(new PairFunction<ConsumerRecord<String, String>, String, String>(){
            private static final long serialVersionUID = 1L;
            @Override
            //获取数据中的 key和value  (ConsumerRecord 保存了完整的kafka中信息  包括分区 偏移量 等)
            public Tuple2<String, String> call(ConsumerRecord<String, String> consumerRecord) throws Exception {
                System.out.println("--------------- ConsumerRecord ------------------------ ");
                System.out.println("partition:   "+consumerRecord.partition());
                System.out.println("offset:   "+consumerRecord.offset());
                System.out.println("toString:   "+consumerRecord.toString());
                Thread.sleep(100);//时间间隔 长只是为了便于查看日志，观察是不是从指定的偏移量开始消费的
                return new Tuple2<>(consumerRecord.key(), consumerRecord.value());
            }
        });
        javaPairDStream.foreachRDD(new VoidFunction<JavaPairRDD<String,String>>() {
            @Override
            public void call(JavaPairRDD<String, String> javaPairRDD) throws Exception {
                // TODO Auto-generated method stub
                javaPairRDD.foreach(new VoidFunction<Tuple2<String,String>>() {
                    @Override
                    public void call(Tuple2<String, String> tuple2)
                            throws Exception {
                        // TODO Auto-generated method stub
                        System.out.println(tuple2._2);
                    }
                });
            }
        });
        streamingContext.start();
        streamingContext.awaitTermination();


    }
}