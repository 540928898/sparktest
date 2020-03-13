package com.sunxj.redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

import static com.sunxj.redis.JedisPool.jedisPool;

public class PaiMingZset {
    public static void main(String[] args) {
        String key = "mostUsedLanguages";
        PaiMingZset pz1 = new PaiMingZset();
        Jedis jedis = JedisPool.getJedis();
        HashMap<String, Double> t1 = new HashMap<String,Double>();
        t1.put("zhoujielun",20.0);
        t1.put("zhangjie",30.0);
        jedis.zadd(key,100,"Java");
        System.out.println("Number of Java users:" + jedis.zscore(key, "Java"));
        System.out.println("Number of elements:" + jedis.zcard(key));
    }
}
