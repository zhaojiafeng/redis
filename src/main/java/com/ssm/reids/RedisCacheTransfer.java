package com.ssm.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by dllo on 17/12/29.
 */
public class RedisCacheTransfer {


    // 这个中间类负责给RedisCache装配工厂对象
    @Autowired
    public void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.setJedisConnectionFactory(jedisConnectionFactory);
    }


}
