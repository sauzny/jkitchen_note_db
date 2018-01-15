/*package com.sauzny.jedis;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.elasticsearch.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;

public class StringShiWangTest extends BaseJunit4Test{

    
    @Autowired
    private JedisFactory jedisFactory;
    
    @Test
    public void push() throws JsonProcessingException{
        
        List<String> list = Lists.newArrayList();
        
        for(int i=0;i<100000;i++){
            String value = RandomStringUtils.randomAlphabetic(128);
            list.add(value);
        }

        ObjectMapper mapper = new ObjectMapper();
        // 序列化用时 60ms
        String jsonlist = mapper.writeValueAsString(list);
        
        try(Jedis jedis = jedisFactory.openJedis()){

            // 插入redis用时 95ms   占用内存 16M
            jedis.set("stringshiwan", jsonlist);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void pull(){
        try(Jedis jedis = jedisFactory.openJedis()){
            // 读取redis用时 90ms
            String str = jedis.get("stringshiwan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
*/