package com.sunxj.redis;

//import org.apache.spark.annotation.AlphaComponent;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.LoggerFactory;
import com.sunxj.redis.RedisTool;
import redis.clients.jedis.Jedis;

import java.util.logging.Logger;

@Service
public class DistributeLock<SetParams> {
    Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());
    private String lock_key = "redis_lock";
    protected long internalLockLeaseTime = 30000;
    private long timeout = 999999;
    Long start = System.currentTimeMillis();

    public boolean lock(String id) {
        Jedis jedis = new Jedis("127.0.0.1");
        String lock = jedis.set(lock_key, id, "NX", "PX", internalLockLeaseTime);
        if ("OK".equals(lock)) {
            return true;
        }
        return true;
    }



    public static void main(String[] args) {



//        RedisTool r1 = new RedisTool();
//        r1.setJedis();


    }
    //    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);

}
