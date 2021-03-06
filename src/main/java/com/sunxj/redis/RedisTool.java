package com.sunxj.redis;

import redis.clients.jedis.Jedis;
import java.net.SocketTimeoutException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;


public class RedisTool {
//    static JedisPool jedis = new JedisPool();
    static Jedis jedis = JedisPool.getJedis();
   public static void setKeys(String key,String value){
           jedis.set(key, value);
           System.out.println(jedis.get(key));
   }
    public void setHash(String hashName,HashMap<String, String> hashMap) {
        jedis.hmset(hashName, hashMap);
        Map new1 = jedis.hgetAll(hashName);
        System.out.println(String.format("setHash Success ,name is %s", hashName));
        for (Object entry:new1.entrySet()
             ) {
            System.out.println(entry.toString());
        }
    }
//    @Deprecated
    public static void setList(String listName,ArrayList<String> arrayList) {
        for (String element:arrayList
             ) {
            jedis.rpush(listName, element);
        }
//        ArrayList<String> a1 = (ArrayList<String>) jedis.lrange(listName, 0, arrayList.size()-1);//List不是一个接口吗
//        for (String ele:a1
//             ) {
//            System.out.println(ele);
//        }
    }

    public void setSet(String setName, Set<String> set) {
        for (String element:set
             ) {
            jedis.sadd(setName, element);
        }
        Set<String> set1 = jedis.smembers(setName);
        for (String ele:set1
             ) {
            System.out.println(ele);
        }
        //获取所有的keys
    }
    public void getAllKeys() {
        System.out.println("获取所有keys");
        Set<String> set2 = jedis.keys("*"); // 阻塞！！！
        for (String ele:set2
        ) {
            System.out.println(ele);
        }
    }

    void zsortGet(String zsName,int topn){
        Set<String> t1 = jedis.zrevrange(zsName,0,4);
        for (String name:t1
             ) {
            System.out.println(name);
        }
    }

//    public void findList(String listName,in){

//    }

    public static void main(String[] args) {

        RedisTool r1 = new RedisTool();

        r1.zsortGet("topPopularFilms",5);

//            ArrayList<String> se = new ArrayList<String>();
//            HashSet<String> set1 = new HashSet<String>();
//            HashMap<String, String> hashMap = new HashMap<String, String>();
//            se.add("gupeng");
//            se.add("xiao");
//            hashMap.put("gupeng", "new1");
//            hashMap.put("tel", "13815690319");
//            set1.add("gupeng1");
//            set1.add("gupeng2");
//            set1.add("gupeng3");
//            System.out.println("setList测试");
//            r1.setList("gupengList", se);
//            System.out.println("setHasht测试");
//            r1.setHash("gupengInfo",hashMap);
//            System.out.println("setSet测试");
//            r1.setSet("gupengSet",(Set<String>)set1);
//            System.out.println("删除测试");
//            r1.getAllKeys();
////            jedis.del("gupengSet:","gupeng1");
//            r1.getAllKeys();
//            jedis.set("gupengLock", "locklock","NX","PX",15);
//            jedis.hset("gupenghset", "gugu", "1");


    }
}
