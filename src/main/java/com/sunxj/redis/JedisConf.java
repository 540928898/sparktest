package com.sunxj.redis;

public class JedisConf {
    static protected long internalLockLeaseTime = 30;
    static final Long RELEASE_SUCCESS = 1L;
    static String IP = "127.0.0.1";
    static int PORT = 6379;
    static int MAX_ACTIVE = 80;  //最大Jedis数量
    static int MAX_IDEL = 25; //最大的懒惰Jedis实例
    static int MAX_TIMEWAIT = -1; //最大等待时间 会抛出JedisConnectiongException
    static int TIMEOUT = 30000;
    static boolean TEST_ON_BORROW = false; //在用一个Jedis的时候是否需要验证
    static boolean TEST_ON_RETURN = false; // 再返回给Jedis的时候是否需要验证 非常影响性能
    public JedisConf(){
    }
}
