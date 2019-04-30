package com.app.timetable.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Hashtable;

/**
 * @author Judith
 * @date 2019/4/29 15:14
 * @description
 */
public class RedisUtil {

    private static String HOST = "47.107.239.122";

    private static int PORT = 6379;

    private static int TIMEOUT = 0;

    private static String CIPHER = "redis123";

    //连接耗尽时是否阻塞，false报异常，true阻塞直到超时（默认）
    private static boolean isBlock = false;

    private static JedisPoolConfig poolConfig = new JedisPoolConfig();

    private static Hashtable<String, JedisPool> jedisTable = new Hashtable<>();

    static {
        // 使用连接池
        // 【2】创建JedisPool所需的连接池配置

        //最大连接数，默认8
        poolConfig.setMaxTotal(64);
        // 最大空闲数,默认8
        poolConfig.setMaxIdle(8);
        // 最小空闲连接数，默认0
        poolConfig.setMinIdle(0);
        // 对象最小空闲时间，默认1800000毫秒(30分钟)
        poolConfig.setMinEvictableIdleTimeMillis(1800000);
        // 获取连接的最大等待毫秒数。如果设为小于0，则永远等待
        poolConfig.setMaxWaitMillis(0);
        // 在创建对象时检测对象是否有效，true是，默认值是false
        poolConfig.setTestOnCreate(true);
        //从对象池获取对象时检测对象是否有效，默认false
        poolConfig.setTestOnBorrow(true);
        //在向对象池中归还对象时是否检测对象有效，true是，默认值是false
        poolConfig.setTestOnReturn(false);
        //在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性。true是，默认值是false
        poolConfig.setTestWhileIdle(false);
        //检测空闲对象线程每次检测的空闲对象的数量。默认值是3；如果这个值小于0,则每次检测的空闲对象数量等于当前空闲对象数量除以这个值的绝对值，并对结果向上取整
        poolConfig.setNumTestsPerEvictionRun(5);
        //是否启用后进先出, 默认true
        poolConfig.setLifo(true);
        //多长时候执行一次空闲对象检测。单位是毫秒数。如果小于等于0，则不执行检测线程。默认值是-1
        poolConfig.setTimeBetweenEvictionRunsMillis(-1);
        //当对象池没有空闲对象时，新的获取对象的请求是否阻塞。true阻塞。默认值是true;
        poolConfig.setBlockWhenExhausted(isBlock);
        //是否启用pool的jmx管理功能, 默认true
        poolConfig.setJmxEnabled(true);
    }

    public static Jedis getJedis(String key) {
        JedisPool jedisPool = jedisTable.get(key);

        if(jedisPool == null || jedisPool.isClosed()) {
            jedisPool = new JedisPool(poolConfig,HOST,PORT,TIMEOUT,CIPHER);
            jedisTable.put(key,jedisPool);
        } else {
            System.out.println(key + " NumActive:" + jedisPool.getNumActive());
        }

        Jedis jedis = null;

        //获取Jedis对象
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }
}
