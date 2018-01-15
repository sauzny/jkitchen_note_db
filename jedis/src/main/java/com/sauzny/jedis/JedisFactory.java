package com.sauzny.jedis;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisFactory {
    
    @Value("${node.ip}")  
    private String ip;
    @Value("${node.port}")  
    private String port;
    @Value("${node.timeout}")  
    private String timeout;
    @Value("${node.password}")  
    private String password;
    @Value("${pool.maxTotal}")  
    private String maxTotal;
    @Value("${pool.maxIdle}")  
    private String maxIdle;
    @Value("${pool.maxWaitMillis}")  
    private String maxWaitMillis;
    @Value("${pool.testOnBorrow}")  
    private String testOnBorrow;
	
    private JedisPool jedisPool;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisFactory.class);
	
    @PostConstruct
    public void init(){

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(Integer.parseInt(maxTotal));
        jedisPoolConfig.setMaxIdle(Integer.parseInt(maxIdle));
        jedisPoolConfig.setMaxWaitMillis(Integer.parseInt(maxWaitMillis));
        jedisPoolConfig.setTestOnBorrow(Boolean.parseBoolean(testOnBorrow));
        jedisPoolConfig.setMinIdle(100);
        
        this.jedisPool = new JedisPool(jedisPoolConfig, ip, Integer.parseInt(port), Integer.parseInt(timeout), password);
        
    }
	
	public JedisPool getJedisPool(){
		return this.jedisPool;
	}

	public Jedis openJedis(){
	    return this.jedisPool.getResource();
	}
}
