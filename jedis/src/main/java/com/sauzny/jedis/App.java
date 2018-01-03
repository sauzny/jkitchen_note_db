package com.sauzny.jedis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * Hello world!
 *
 */
public class App {
    
    public static void main(String[] args) {

        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        // Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("192.168.73.128", 7000));
        jedisClusterNodes.add(new HostAndPort("192.168.73.128", 7001));
        jedisClusterNodes.add(new HostAndPort("192.168.73.131", 7002));
        jedisClusterNodes.add(new HostAndPort("192.168.73.131", 7003));
        jedisClusterNodes.add(new HostAndPort("192.168.73.132", 7004));
        jedisClusterNodes.add(new HostAndPort("192.168.73.132", 7005));
        
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
        
        // JedisCluster 目前还不支持  pipeline
        
        // 按照 jedis 的风格来说 ，只实现 redis 标准API，估计也不会更新到支持吧……
        
        // 这个测试数据 是 使用Redisson的json形式插入的数据
        
        System.out.println(jc.sismember("吉林", "\"15004925292\""));
        System.out.println(jc.sismember("上海", "\"15608238708\""));
        System.out.println(jc.sismember("云南", "\"15303568778\""));
        
    }
}
