package com.sauzny.jedis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * *************************************************************************
 * @文件名称: ListRedisImpl.java
 *
 * @包路径  : com.xxx888.promotion.redis
 *				 
 * @版权所有: xxx888（北京）科技有限公司 (C) 2014
 *
 * @类描述:  字符链表（List）数据类型接口
 * 
 * @创建人:   liujinxin  
 *
 * @创建时间: 2014年6月11日 - 下午4:35:42 
 *
 * @修改记录:
   -----------------------------------------------------------------------------------------------
             时间						|		修改人		|		修改的方法		|		修改描述                                                                
   -----------------------------------------------------------------------------------------------
							|					|					|                                       
   ----------------------------------------------------------------------------------------------- 	
 
 **************************************************************************
 */
public class ListRedisManager extends BaseRedisManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(ListRedisManager.class);

	public ListRedisManager(JedisPool jedisPool) {
		super(jedisPool);
	}

	/**
	 * 
	 *  Function:
	 *  功能说明：插入元素
	 *	 使用说明：从链表的尾部插入
	 *  @author  liujinxin  DateTime 2014年6月11日 下午3:18:33
	 *	 返回类型: Long    
	 *  @param key
	 *  @param string
	 *  @return   列表内的元素数目
	 */
	public Long rpush(final String key, final String... string){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.rpush(key, string);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：删除指定的list元素
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午3:47:17
	 *	 返回类型: Long    
	 *  @param keys
	 *  @return
	 */
	public Long del(final String... keys){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.del(keys);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：删除前count个值等于value的元素。
	 *  		如果count大于0，从头向尾遍历并删除，
	 *  		如果count小于0，则从尾向头遍历并删除。
	 *  		如果count等于0，则删除链表中所有等于value的元素。
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午4:17:38
	 *	 返回类型: Long    
	 *  @param key
	 *  @param count
	 *  @param value
	 *  @return	返回被删除的元素数量。
	 *  		如果指定的Key不存在，则直接返回0,
	 */
	public Long lrem(final String key, long count, final String value){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.lrem(key, count, value);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：更新指定位置的元素值
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午4:13:15
	 *	 返回类型: String    
	 *  @param key
	 *  @param index
	 *  @param value
	 *  @return   返回 正确或者错误 代码
	 */
	public String lset(final String key, final long index, final String value){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.lset(key,index,value);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：获取指定list的长度
	 *	 使用说明：如果key不存在，则返回0
	 *  @author  liujinxin  DateTime 2014年6月11日 下午3:52:55
	 *	 返回类型: Long    
	 *  @param key
	 *  @return
	 */
	public Long llen(final String key){
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.llen(key);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：获取指定范围的元素
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午3:31:47
	 *	 返回类型: List<String>    
	 *  @param key
	 *  @param start
	 *  @param end
	 *  @return
	 */
	public List<String> lrange(String key, Long start, Long end){
		List<String> result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.lrange(key, start, end);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	
	/**
	 *  Function:
	 *  功能说明：该命令将返回链表中指定位置(index)的元素，index是0-based，表示头部元素，如果index为-1，表示尾部元素。
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午4:20:53
	 *	 返回类型: String    
	 *  @param key
	 *  @param index
	 *  @return	如果与该Key关联的不是链表，该命令将返回相关的错误信息。
	 */
	public String lindex(final String key, final long index){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.lindex(key, index);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：弹出头元素
	 *	 使用说明：
	 *  @author  liujinxin  DateTime 2014年6月11日 下午4:09:45
	 *	 返回类型: String    
	 *  @param key
	 *  @return
	 */
	public String lpop(final String key){
		String result = null;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			result = jedis.lpop(key);
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
	}
	
	/**
	 *  Function:
	 *  功能说明：设置某个key的过期时间（单位：秒）,在超过该时间后，Key被自动的删除。如果该Key在超时之前被修改，与该键关联的超时将被移除。
	 *	 使用说明：长度为1的时候设置
	 *  @author  liujinxin  DateTime 2014年7月9日 下午4:57:25
	 *	 返回类型: Long    
	 *  @param key
	 *  @param seconds
	 *  @return
	 */
	public Long firstExpire(final String key, final int seconds){
		
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = this.getJedisPool().getResource();
			if(jedis.llen(key) == 1){
				result = jedis.expire(key, seconds);
			}
		}catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
        } finally {
        	this.getJedisPool().returnResource(jedis);
        }
		return result;
		
	}
}
