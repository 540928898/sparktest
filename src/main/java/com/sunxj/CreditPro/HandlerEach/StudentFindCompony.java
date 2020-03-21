package com.sunxj.CreditPro.HandlerEach;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.ArrayList;

public class StudentFindCompony implements HandlerInt {
    public ArrayList<String> getRedisInfo(){
        ArrayList<String> t1 = new ArrayList();
        t1.add("gupeng");
        System.out.println("this is redisInfo");
        return t1;
    }
    public ArrayList<String> getHbaseInfo(){
        ArrayList<String> t1 = new ArrayList();
        t1.add("gupeng");
        System.out.println("this is HbaseInfo");
        return t1;
    }
    public ArrayList<String> getMysqlInfo(){
        ArrayList<String> t1 = new ArrayList();
        t1.add("gupeng");
        System.out.println("this is MysqlInfo");
        return t1;
    }
    @Override
    public void DoHandle(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("this is StudentFindCompony!");
    }
    /**
     * 接收学生端发送的数据，从redis中取到热点数据，包括近期爱好，近期购物车信息，从hbase中取到历史特征数据，
     * 从mysql取到公司数据与学生基本信息后预测模型，存入Redis。学生端从客户端去查。可以加入timestamp 做到去重
     *  点击了以后就去redis建立一个key  如果有key 那么就不能建立了 不能提交
     * 用分布式锁  去抢idInv:Lock() 没抢到，放弃，抢到了（为了防止重复计算）浪费资源
     *  直接去redis查 没有的话，多个consumer 抢redis锁 抢到了就去redis hbase，mysql查数据 然后拼接成为arraylist，进行预测。预测完的结果存入redis
     *  释放锁
     *
     *  预测结果：idInv:Res value是String类型
     */
}
