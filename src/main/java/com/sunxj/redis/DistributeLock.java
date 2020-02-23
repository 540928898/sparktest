package com.sunxj.redis;

//import org.apache.spark.annotation.AlphaComponent;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.LoggerFactory;
import com.sunxj.redis.RedisTool;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collections;
import java.util.logging.Logger;

@Service
public class DistributeLock {
    static protected long internalLockLeaseTime = 30;
    private static final Long RELEASE_SUCCESS = 1L;
    private static String IP = "127.0.0.1";
    private static int PORT = 6379;
    private  static int MAX_ACTIVE = 80;  //最大Jedis数量
    private static int MAX_IDEL = 25; //最大的懒惰Jedis实例
    private  static int MAX_TIMEWAIT = -1; //最大等待时间 会抛出JedisConnectiongException
    private static int TIMEOUT = 30000;
    private static boolean TEST_ON_BORROW = false; //在用一个Jedis的时候是否需要验证
    private static boolean TEST_ON_RETURN = false; // 再返回给Jedis的时候是否需要验证 非常影响性能
    public static JedisPool jedisPool = null;
    public static void initJedisPool(){
        try {
            JedisPoolConfig conf = new JedisPoolConfig();
            conf.setMaxTotal(MAX_ACTIVE);
            conf.setMaxIdle(MAX_IDEL);
            conf.setMaxWaitMillis(MAX_TIMEWAIT);
            conf.setTestOnBorrow(TEST_ON_BORROW);
            conf.setTestOnReturn(TEST_ON_RETURN);
            jedisPool = new JedisPool(conf, IP, PORT, TIMEOUT);
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

    public  static boolean lock(String lock_key,String requestId) {
       // NX 为 SET if not success
        // PX:set expir time
        try {
            String lock = getJedis().set(lock_key, requestId, "NX", "PX", internalLockLeaseTime);
            if ("OK".equals(lock)) {
                return true;
            }
            return false;
        }catch (Exception e){
//            e.printStackTrace();
            return  false;
        }
    }

    public  static boolean releaseLock(String lock_key,String requestId){
        try {
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
            Object result = getJedis().eval(script, Collections.singletonList(lock_key), Collections.singletonList(requestId));
            if (RELEASE_SUCCESS.equals(result)) {
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
        }

}
