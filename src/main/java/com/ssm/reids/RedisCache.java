package com.ssm.reids;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by dllo on 17/12/29.
 */
public class RedisCache implements Cache {

    private final String id;

    private ReadWriteLock readWriteLock;

    private static JedisConnectionFactory jedisConnectionFactory;


    public static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        RedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

    public RedisCache(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id不能为空");
        }
        this.id = id;
    }


    // 获取缓存对象的唯一地址
    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 保存key，value到缓存对象中
     * key可以是任何对象，但一般是CacheKey对象
     * value是查询结果，一般是list
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {

        JedisConnection jedisConnection = null;

        try {
            // 通过工厂创建Redis的连接
            jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();

            // 创建序列化的工具类，用于将实现类serializer接口的对象转为byte[]
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            // 通过set方法吧key，value写入到Redis
            // 把key，value序列化，转成byte数组格式
            jedisConnection.set(serializer.serialize(key), serializer.serialize(value));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
    }

    /**
     * 从缓存Redis中获取key对应的value
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        Object result = null;
        JedisConnection jedisConnection = null;

        try {
            // 通过工厂创建Redis的连接
            jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();

            // 创建序列化的工具类，用于将实现类serializer接口的对象转为byte[]
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            // 通过get方法从Redis中获取key对应的value
            result = serializer.deserialize(jedisConnection.get(serializer.serialize(key)));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return result;
    }

    /**
     * 根据key移除value
     * 可选的实现方法，没有被核心框架调用
     *
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        Object result = null;
        JedisConnection jedisConnection = null;

        try {
            // 通过工厂创建Redis的连接
            jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();

            // 创建序列化的工具类，用于将实现类serializer接口的对象转为byte[]
            RedisSerializer<Object> serializer = new JdkSerializationRedisSerializer();

            // 根据key删除key-value的键值对
            jedisConnection.expire(serializer.serialize(key), 0);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return result;
    }

    @Override
    public void clear() {
        JedisConnection jedisConnection = null;

        try {
            // 通过工厂创建Redis的连接
            jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();

            jedisConnection.flushAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
    }

    @Override
    public int getSize() {

        Integer count = 0;

        JedisConnection jedisConnection = null;

        try {
            // 通过工厂创建Redis的连接
            jedisConnection = (JedisConnection) jedisConnectionFactory.getConnection();

            count = jedisConnection.dbSize().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            if (jedisConnection != null) {
                jedisConnection.close();
            }
        }
        return count;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }
}
