package com.gupeng.scala
import java.util.{Collections, Properties}

import kafka.serializer.StringDecoder
//import net.sf.json.JSONObject
import org.apache.hadoop.hbase.client.{HTable, Put}
import org.apache.hadoop.hbase.util.Bytes
import org.apache.hadoop.hbase.{HBaseConfiguration, TableName}
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.kafka.KafkaUtils
import scala.collection.mutable.ListBuffer
//import org.apache.spark.streaming.Seconds
import scala.util.parsing.json.JSONObject
object ConsumerCOn {

  def main(args: Array[String]): Unit = {
    val prop = new Properties
    val brokers = "localhost:9092"
    prop.put("bootstrap.servers", brokers)
    // 指定消费者组
    prop.put("group.id", "test-consumer-group")
    // 指定消费位置: earliest/latest/none
    prop.put("auto.offset.reset", "latest")
    // 指定消费的key的反序列化方式
    prop.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    // 指定消费的value的反序列化方式
    prop.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    prop.put("enable.auto.commit", "true")
    prop.put("session.timeout.ms", "30000")
    // 得到Consumer实例
    val kafkaConsumer = new KafkaConsumer[String, String](prop)
    // 首先需要订阅topic
    kafkaConsumer.subscribe(Collections.singletonList("test1"))
    // 开始消费数据
    while (true) {
      // 如果Kafak中没有消息，会隔timeout这个值读一次。比如上面代码设置了2秒，也是就2秒后会查一次。
      // 如果Kafka中还有消息没有消费的话，会马上去读，而不需要等待。
      val msgs: ConsumerRecords[String, String] = kafkaConsumer.poll(2000)
      // println(msgs.count())
      val it = msgs.iterator()
      while (it.hasNext) {
        val msg = it.next()
        println(s"partition: ${msg.partition()}, offset: ${msg.offset()}, key: ${msg.key()}, value: ${msg.value()}")
      }
    }
  }

}
