package com.sauzny.jedis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

public class MainTest extends BaseJunit4Test{

    @Autowired
    private JedisFactory jedisFactory;
    
    @Test
    public void foo01(){
        
        
        try(Jedis jedis = jedisFactory.openJedis()){
            jedis.set("a", "a");
        }
        
        
        /*
        //实例化一个jedis对象，连接到指定的服务器，指定连接端口号
        Jedis jedis = new Jedis("127.0.0.1",6379);
        //jedis.auth("gbasebipw");
        //将key为message的信息写入redis数据库中
        jedis.set("message", "Hello Redis!");
        //从数据库中取出key为message的数据
        String value = jedis.get("message");
        System.out.println(value);
        //关闭连接
        jedis.close();
        */
    }
}
