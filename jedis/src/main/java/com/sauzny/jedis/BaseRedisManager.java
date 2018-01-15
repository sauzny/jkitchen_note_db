package com.sauzny.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * *************************************************************************
 * @文件名称: BaseRedisManager.java
 *
 * @包路径  : com.xxx888.promotion.redis 
 *				 
 * @版权所有: xxx888（北京）科技有限公司 (C) 2014
 *
 * @类描述:  
 * 
 * @创建人:   liujinxin  
 *
 * @创建时间: 2014年7月18日 - 下午4:31:05 
 *
 * @修改记录:
   -----------------------------------------------------------------------------------------------
             时间						|		修改人		|		修改的方法		|		修改描述                                                                
   -----------------------------------------------------------------------------------------------
							|					|					|                                       
   ----------------------------------------------------------------------------------------------- 	
 
 **************************************************************************
 */
public class BaseRedisManager {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRedisManager.class);
	
	private JedisPool jedisPool;
	
	public JedisPool getJedisPool(){
		return this.jedisPool;
	}
	
	public BaseRedisManager(JedisPool jedisPool){
		this.jedisPool = jedisPool;
	}
	
	/**
	 *  Function:
	 *  功能说明：设置某个key的过期时间（单位：秒）,在超过该时间后，Key被自动的删除。如果该Key在超时之前被修改，与该键关联的超时将被移除。
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年7月9日 下午4:57:25
	 *	 返回类型: Long    
	 *  @param key
	 *  @param seconds
	 *  @return
	 */
	public Long expire(final String key, final int seconds){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			result = jedis.expire(key, seconds);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
            jedisPool.returnResource(jedis);
        }
		return result;
	}
	
	public Long del(String key){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.del(key);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException("redis，操作异常，key="+key);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
}
