package com.gupeng.scala.kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkStreamingKafka {
//  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
//  val ssc = new StreamingContext(conf, Seconds(1))
//  val lines = ssc.socketTextStream("localhost", 9999)
//  val words = lines.flatMap(_.split(" "))
//  import org.apache.spark.streaming.StreamingContext._ // not necessary since Spark 1.3
//  val pairs = words.map(word => (word, 1))
//  val wordCounts = pairs.reduceByKey(_ + _)
//  wordCounts.print()
//  ssc.start()
def main(args: Array[String]): Unit = {
  val spark = SparkSession.builder().appName("spark_kafka").master("local[1]").getOrCreate()
  val batchDuration = Seconds(5) //时间单位为秒
  val streamContext = new StreamingContext(spark.sparkContext, batchDuration)
  streamContext.checkpoint("/Users/4paradigm/Desktop/checkpoint")
  var topics = Array("Three").toSet
  val kafkaParams = Map[String, String]("metadata.broker.list" -> "localhost:9092")
  val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamContext, kafkaParams, topics)
  stream.foreachRDD(rdd => {
    rdd.foreach(line => {
      println("key=" + line._1 + "  value=" + line._2)
    })
  })
  streamContext.start()  //spark stream系统启动
  streamContext.awaitTermination() //

}

}
