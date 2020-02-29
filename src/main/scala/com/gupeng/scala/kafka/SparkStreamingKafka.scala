package com.gupeng.scala.kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.codehaus.jettison.json.JSONObject
import com.gupeng.scala.RedisUitls.RedisClient
object SparkStreamingKafka {
  final val tableName = "111"
//  def addUserClick(tableName:String,userName:String,clickCount:Int):Unit={
//    RedisTools.setHincrBy(tableName,userName,clickCount)
//  }
  def main(args: Array[String]): Unit = {
    val dbIndex = 1
    val spark = SparkSession.builder().appName("spark_kafka").master("local[1]").getOrCreate()
    val batchDuration = Seconds(5) //时间单位为秒
    val streamContext = new StreamingContext(spark.sparkContext, batchDuration)
    streamContext.checkpoint("F:\\sparkCheckPoint")
    var topics = Array("test").toSet
    val kafkaParams = Map[String, String]("metadata.broker.list" -> "localhost:9092")
    val stream = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](streamContext, kafkaParams, topics)
    val events = stream.flatMap(line =>{
      val data = new JSONObject(line._2)
      Some(data)
    })
    val userClick = events.map(x => (x.getString("uid"),x.getInt("click_count"))).reduceByKey(_+_)


    userClick.foreachRDD( line =>{
      line.foreach(pair=>{
        println("this is each partiotion : "+pair._1+" countClick : "+ pair._2)
        val jedis = RedisClient.pool.getResource
        jedis.hincrBy(tableName, pair._1, pair._2)
        RedisClient.pool.returnResource(jedis)
      })

//      userClick.foreachRDD( line =>{
//      line.foreachPartition(partionOfEachRecord =>{
//        partionOfEachRecord.foreach(pair =>{
//          println("this is each partiotion : "+pair._1+" countClick : "+ pair._2)
//          val uid = pair._1
//          val clickCount = pair._2
//          val jedis = RedisClient.pool.getResource
////          jedis.select(dbIndex)
//          jedis.hincrBy(tableName, uid, clickCount)
//          RedisClient.pool.returnResource(jedis)
//        })
//      })
    })

    streamContext.start()  //spark stream系统启动
    streamContext.awaitTermination() //
  }

}

