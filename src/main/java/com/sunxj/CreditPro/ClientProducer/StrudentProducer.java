package com.sunxj.CreditPro.ClientProducer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunxj.kafka.KafkaControl;
import org.apache.hadoop.ipc.RetriableException;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.protocol.types.Field;
import org.codehaus.jettison.json.JSONException;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
public class StrudentProducer {
    public final static String Group_ID = "test-consumer-group";
    public final static String TOPIC = "StudentGetJob";
//    public final static String TOPIC = "test";
    public final static int BUFFER_SIZE = 64 * 1024;
    public final static int TIMEOUT = 20000;
    public final static int INTERVAL = 10000;
    public final static String BROKER_LIST = "localhost:9092,localhost:9093,localhost:9094";
    public final static int GET_MEG_INTERVAL = 1000;
    private static Random random =new Random();
    private int  pointer = -1;
    private String[] courses = new String[]{
            "Gaoshu", "English",
            "Kongyuan", "History"};
    private static String[] studentID = new String[]{
            "f76aac1e", "d008915b",
            "7d270fdf", "c45cb47c",
            "b2fe21f8", "0bbf58d7"};
    public static int getScore(String course){
        return random.nextInt(100);
    }
    public static int getCount(){return random.nextInt(50);}
    public static void runProducer() {
        Properties kafkaProps = new Properties();
        kafkaProps.put("bootstrap.servers", BROKER_LIST);
        kafkaProps.put("acks","-1");
        kafkaProps.put("batch.size",1048576);
        kafkaProps.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
//        kafkaProps.put("partitioner.class","com.sunxj.kafka.PartitionNew");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(kafkaProps);
        int i = 0;
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i += 1;

            JSONObject t1 = new JSONObject();
            try{
                t1.put("uid", studentID[new Random().nextInt(studentID.length)]);//随机生成用户id
                t1.put("Gaoshu", getScore("Gaoshu")); //记录时间发生时间
                t1.put("English", getScore("English")); //记录时间发生时间
                t1.put("Kongyuan", getScore("Kongyuan")); //记录时间发生时间
                t1.put("History", getScore("History")); //记录时间发生时间
                t1.put("click_count", getCount()); //获取志愿次数
            } catch(Exception e){
                e.printStackTrace();
            }
            producer.send(new ProducerRecord<String, String>(TOPIC, "" + i, t1.toString()), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null){
                        System.out.println("发送成功");
                    }
                    else {
                        if (e instanceof RetriableException){
                            System.out.println("可重试");
                        }else System.out.println("不可重试");
                    }
                }
            });
            System.out.println("produce success:"+TOPIC+"" + i+ ":asd"+t1.toString());
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(StrudentProducer::runProducer) ;
        t1.start();
    }
}
