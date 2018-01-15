/*package com.sauzny.jedis;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.sauzny.jkitchen_note.metrics.TestTimers;

import redis.clients.jedis.Jedis;

public class PushTest extends BaseJunit4Test{


    *//**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     *//*
    private static final MetricRegistry metrics = new MetricRegistry();
    
    *//**
     * 在控制台上打印输出
     *//*
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
    
    *//**
     * 实例化一个Meter
     *//*
    //private static final Timer requests = metrics.timer(name(TestTimers.class, "request"));
    private static final Timer requests = metrics.timer(MetricRegistry.name(TestTimers.class, "request"));
    
    @Autowired
    private JedisFactory jedisFactory;
    
    @Test
    public void push(){
        
        reporter.start(3, TimeUnit.SECONDS);
        


        ExecutorService service = Executors.newFixedThreadPool(40);
        
        for (int i=0; i<1000000; i++) {
            
            service.execute(new WorkerA(jedisFactory.openJedis(), requests));
        }
        
        
        try(Jedis jedis = jedisFactory.openJedis()){
            while(true){
                String value = RandomStringUtils.randomAlphabetic(16);
                jedis.rpush("testList01", value);
                long len = jedis.llen("testList01");
                System.out.println("队列rpush插入：" + value + " | 队列现在的长度是：" + len);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        while(true){
            
        }
    }
    
}

class WorkerA implements Runnable {

    private Jedis jedis;
    private Timer requests;
    
    public WorkerA(Jedis jedis, Timer requests) {
        super();
        this.jedis = jedis;
        this.requests = requests;
    }


    @Override
    public void run() {
        
        String value = RandomStringUtils.randomAlphabetic(128);

        Timer.Context context = requests.time();
        jedis.rpush("testList01", value);
        context.stop();
        
        jedis.close();
    }
    
}*/