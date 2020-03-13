package com.sunxj.CreditPro.HandlerEach;

public class StudentFindCompony {
    /**
     * 接收学生端发送的数据，从redis中取到热点数据，包括近期爱好，近期购物车信息，从hbase中取到历史特征数据，
     * 从mysql取到公司数据与学生基本信息后预测模型，存入Redis。学生端从客户端去查。可以加入timestamp 做到去重
     *  点击了以后就去redis建立一个key  如果有key 那么就不能建立了 不能提交
     */
}
