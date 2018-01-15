package com.sauzny.jedis;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * *************************************************************************
 * @文件名称: HashRedisManager.java
 *
 * @包路径  : com.xxx888.promotion.redis 
 *				 
 * @版权所有: xxx888（北京）科技有限公司 (C) 2014
 *
 * @类描述:  redis中hash类型操作
 * 
 * @创建人:   liujinxin  
 *
 * @创建时间: 2014年9月2日 - 下午2:14:09 
 *
 * @修改记录:
   -----------------------------------------------------------------------------------------------
             时间						|		修改人		|		修改的方法		|		修改描述                                                                
   -----------------------------------------------------------------------------------------------
							|					|					|                                       
   ----------------------------------------------------------------------------------------------- 	
 
 **************************************************************************
 */
public class HashRedisManager extends BaseRedisManager{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HashRedisManager.class);
	
	public HashRedisManager(JedisPool jedisPool) {
		super(jedisPool);
	}
	
	public Long hset(String key, String field, String value){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.hset(key, field, value);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	public String hget(String key, String field){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.hget(key, field);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	public String hmset(String key, Map<String, String> hash){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.hmset(key, hash);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	public Map<String, String> hgetAll(String key){
		Map<String, String> result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.hgetAll(key);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  @author  liujinxin  DateTime 2014年11月19日 下午4:10:23
	 *  功能说明：hdel(key, field)：删除名称为key的hash中键为field的域
	 *	使用说明：
	 *	返回类型: Long    
	 *  @param key
	 *  @param fields
	 *  @return
	 */
	public Long hdel(String key, String... fields){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.hdel(key, fields);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
}
