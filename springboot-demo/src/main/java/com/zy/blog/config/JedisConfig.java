package com.zy.blog.config;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Classname JedisConfig
 * @Description TODO
 * @Date 2021/9/7 23:08
 * @Created by 飞燕
 */
@Configuration
@Data
public class JedisConfig {

    private Logger jedisConfigLogger = LoggerFactory.getLogger(JedisConfig.class);

    @Value("${shiro-redis.redis-manager.host}")
    private String host;

    @Value("${shiro-redis.redis-manager.port}")
    private Integer port;
    @Value("${shiro-redis.redis-manager.passord}")
    private  String password;
    @Value("${shiro-redis.redis-manager.database}")
    private int database;
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        //最大空闲数
        jedisPoolConfig.setMaxIdle(300);
        //连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(1000);
        //最近建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(1000);
        //逐条连接的最小空闲时间 默认1800000毫秒（30分钟）
        jedisPoolConfig.setMinEvictableIdleTimeMillis(300000);
        //每次逐出检查时，最大逐出数据
        jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
        //是否在从连接池取出连接前进行检验
        jedisPoolConfig.setTestOnBorrow(true);
        //在空闲时间检查有效性
        jedisPoolConfig.setTestWhileIdle(true);
        return jedisPoolConfig;
    }
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);

        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);

        jedisConnectionFactory.setPassword(password);

        jedisConnectionFactory.setTimeout(5000);
        return jedisConnectionFactory;
    }
}