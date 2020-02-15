package com.sunxj.redis;

import java.util.HashMap;
import java.util.Map;

public class PaiMingZset extends RedisTool{
    public PaiMingZset() {
        super();
    }
    public static void main(String[] args) {
        String key = "mostUsedLanguages";
        PaiMingZset pz1 = new PaiMingZset();
        pz1.setJedis();
        HashMap<String, Double> t1 = new HashMap<String,Double>();
        t1.put("zhoujielun",20.0);
        t1.put("zhangjie",30.0);
        pz1.jedis.zadd(key,t1);
        pz1.jedis.zadd(key,100,"Java");
        System.out.println("Number of Java users:" + pz1.jedis.zscore(key, "Java"));
        System.out.println("Number of elements:" + jedis.zcard(key));
    }
}
