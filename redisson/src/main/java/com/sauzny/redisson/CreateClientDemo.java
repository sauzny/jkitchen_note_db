package com.sauzny.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;

import org.junit.Test;

public class CreateClientDemo {

    
    // wiki地址
    // https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95
    
    /*
     * 创建操作客户端的集中模式
     * 
     * 集群模式
     * 云托管模式
     * 单Redis节点模式
     * 哨兵模式
     * 主从模式
     */
    
    /**
     * @描述: 集群模式
     * @return
     * @返回 RedissonClient
     * @创建人  ljx 创建时间 2018年1月2日 下午3:09:24
     */
    public static RedissonClient cluster(){
        Config config = new Config();
        ClusterServersConfig csc =  config.useClusterServers();
        csc.setScanInterval(2000)  // 集群状态扫描间隔时间，单位是毫秒
            //可以用"rediss://"来启用SSL连接
            .addNodeAddress("redis://192.168.73.128:7000", "redis://192.168.73.128:7001")
            .addNodeAddress("redis://192.168.73.131:7002", "redis://192.168.73.131:7003")
            .addNodeAddress("redis://192.168.73.132:7004", "redis://192.168.73.132:7005");
        
        return Redisson.create(config);
    }
    
    @Test
    public void foo01(){
        CreateClientDemo.cluster();
    }
    
}
