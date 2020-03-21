package com.gupeng.scala.Utils

import java.util.Properties

import kafka.serializer.StringDecoder
import org.apache.kafka.common.TopicPartition
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.dstream.DStream
object KafkaUtil {
//  def getKafakaStream(ssc: StreamingContext, groupId: String, topics: String, fromOffsets: Map[TopicPartition, Long], isNewCluster: Boolean = false): DStream[String] = {
//    val spark = SparkSession.builder().appName("spark_kafka").master("local[1]").getOrCreate()
//    val batchDuration = Seconds(5) //时间单位为秒
//    val streamContext = new StreamingContext(spark.sparkContext, batchDuration)
//    streamContext.checkpoint("/Users/4paradigm/Desktop/checkpoint")
//    var topicsSet = topics.split(",").toSet
//    val kafkaParams = Map[String, String]("metadata.broker.list" -> "localhost:9092")
//
//    if (fromOffsets.isEmpty) {
//      val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamContext, kafkaParams, topicsSet)
//    }
//    return stream
//  }
}
