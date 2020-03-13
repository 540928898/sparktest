package com.sunxj.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPool extends JedisConf{
    public static redis.clients.jedis.JedisPool jedisPool = null;
    public static void initJedisPool(){
        try {
            JedisPoolConfig conf = new JedisPoolConfig();
            conf.setMaxTotal(MAX_ACTIVE);
            conf.setMaxIdle(MAX_IDEL);
            conf.setMaxWaitMillis(MAX_TIMEWAIT);
            conf.setTestOnBorrow(TEST_ON_BORROW);
            conf.setTestOnReturn(TEST_ON_RETURN);
            jedisPool = new redis.clients.jedis.JedisPool(conf, IP, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static synchronized void poolInit() {
        if(jedisPool == null) {
            initJedisPool();
        }
    }
    public synchronized static Jedis getJedis(){
        if(jedisPool ==null){
            poolInit();
        }
        Jedis jedis1 = null;
        try {
            jedis1 = jedisPool.getResource();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            returnResource(jedis1);
        }
        return  jedis1;

    }
    public static void returnResource(final Jedis jedis) {
        if(jedis != null&& jedisPool !=null) {
            try {
                jedis.close();
            } catch (Exception e) {
                e.printStackTrace();
//                log.error("释放jedis资源出错，将要关闭jedis，异常信息：" + e.getMessage());
                if (jedis != null) {
                    try {
                        // 2. 客户端主动关闭连接
                        jedis.disconnect();
                    } catch (Exception e1) {
                        System.out.println("");
//                        log.error("disconnect jedis connection fail: " , e);
                    }finally {
                    }
                }
            }

        }
    }

}
