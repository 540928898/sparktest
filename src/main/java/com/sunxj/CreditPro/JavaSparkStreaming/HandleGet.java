package com.sunxj.CreditPro.JavaSparkStreaming;

import com.sunxj.CreditPro.HandlerEach.HandlerInt;
import com.sunxj.CreditPro.HandlerEach.StudentAction;
import com.sunxj.CreditPro.HandlerEach.StudentFindCompony;
import com.sunxj.javatest.MultyThread.Handler;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import scala.Tuple2;

public class HandleGet {
    private static HandlerInt handlerInt;
    public static HandlerInt getHandler(ConsumerRecord<String, String> consumerRecord) {
        String topic = consumerRecord.topic();
        if(topic.contains("StudentGetJob")){
            handlerInt = new StudentAction();
        }
        else  if (topic.contains("test")){
            handlerInt = new StudentFindCompony();
        }
        return handlerInt;
    }
}
