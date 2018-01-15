package com.sauzny.jedis;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;

public class PullTest extends BaseJunit4Test{

    @Autowired
    private JedisFactory jedisFactory;
    
    @Test
    public void pull(){
        ExecutorService service = Executors.newFixedThreadPool(4);
        for(int i=0;i<10;i++){
            Jedis jedis = jedisFactory.openJedis();
            service.execute(new Worker(jedis));
        }
        
        while(true){
            
        }
    }

}

class Worker implements Runnable{
    
    private Jedis jedis;

    public Worker(Jedis jedis) {
        this.jedis = jedis;
    }
    
    @Override
    public void run() {
        
        try(Jedis jedis = this.jedis){
            while(true){
                List<String> list = jedis.blpop(1000, "testList01");
                System.out.println("线程ID："+Thread.currentThread().getId()+ " | 获取信息：" + list);
                //Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
