package com.sauzny.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * *************************************************************************
 * @文件名称: StringRedisManager.java
 *
 * @包路径  : com.xxx888.promotion.redis 
 *				 
 * @版权所有: xxx888（北京）科技有限公司 (C) 2014
 *
 * @类描述:  
 * 
 * @创建人:   liujinxin  
 *
 * @创建时间: 2014年7月18日 - 下午4:32:09 
 *
 * @修改记录:
   -----------------------------------------------------------------------------------------------
             时间						|		修改人		|		修改的方法		|		修改描述                                                                
   -----------------------------------------------------------------------------------------------
							|					|					|                                       
   ----------------------------------------------------------------------------------------------- 	
 
 **************************************************************************
 */
public class StringRedisManager extends BaseRedisManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(StringRedisManager.class);

	public StringRedisManager(JedisPool jedisPool) {
		super(jedisPool);
	}
	/**
	 *  Function:
	 *  功能说明：设置
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年7月18日 下午4:34:25
	 *	 返回类型: void    
	 *  @param key
	 *  @param value
	 */
	public void set(final String key, final String value){
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			jedis.set(key, value);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
	}
	
	/**
	 *  Function:
	 *  功能说明：获取
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年7月18日 下午4:34:32
	 *	 返回类型: String    
	 *  @param key
	 *  @return
	 */
	public String get(final String key){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = new String(jedis.get(key));
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
}
