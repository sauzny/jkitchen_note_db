package com.sauzny.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.JsonJacksonCodec;
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
    
    /*
org.redisson.codec.JsonJacksonCodec Jackson JSON 编码 默认编码
org.redisson.codec.AvroJacksonCodec Avro 一个二进制的JSON编码
org.redisson.codec.SmileJacksonCodec    Smile 另一个二进制的JSON编码
org.redisson.codec.CborJacksonCodec CBOR 又一个二进制的JSON编码
org.redisson.codec.MsgPackJacksonCodec  MsgPack 再来一个二进制的JSON编码
org.redisson.codec.KryoCodec    Kryo 二进制对象序列化编码
org.redisson.codec.SerializationCodec   JDK序列化编码
org.redisson.codec.FstCodec FST 10倍于JDK序列化性能而且100%兼容的编码
org.redisson.codec.LZ4Codec LZ4 压缩型序列化对象编码
org.redisson.codec.SnappyCodec  Snappy 另一个压缩型序列化对象编码
org.redisson.client.codec.StringCodec   纯字符串编码（无转换）
org.redisson.client.codec.LongCodec 纯整长型数字编码（无转换）
     */
    
    /**
     * @描述: 集群模式
     * @return
     * @返回 RedissonClient
     * @创建人  ljx 创建时间 2018年1月2日 下午3:09:24
     */
    public static RedissonClient cluster(){
        
        Config config = new Config();
        
        // 设置编码类型，实际存储到redis server中的值会被codec（key不是被codec）
        // 默认是json格式，举例：set("A","a")，实际存储到redis中的是带有双引号的a，也就是 A - "a"
        // config.setCodec(JsonJacksonCodec.INSTANCE);
        
        // 有很多参数可以设置
        // config.set

        ClusterServersConfig csc =  config.useClusterServers();
        csc.setScanInterval(2000)  // 集群状态扫描间隔时间，单位是毫秒
            //可以用"rediss://"来启用SSL连接
            .addNodeAddress("redis://192.168.73.128:7000", "redis://192.168.73.128:7001")
            .addNodeAddress("redis://192.168.73.131:7002", "redis://192.168.73.131:7003")
            .addNodeAddress("redis://192.168.73.132:7004", "redis://192.168.73.132:7005");
        
        // csc 可以设置很多参数
        // 连接池相关，密码，超时等信息
        
        return Redisson.create(config);
    }
    
    
    public static RedissonReactiveClient clusterReactive(){
        
        Config config = new Config();

        config.useClusterServers().setScanInterval(2000)
            .addNodeAddress("redis://192.168.73.128:7000", "redis://192.168.73.128:7001")
            .addNodeAddress("redis://192.168.73.131:7002", "redis://192.168.73.131:7003")
            .addNodeAddress("redis://192.168.73.132:7004", "redis://192.168.73.132:7005");
        
        return Redisson.createReactive(config);
    }
    
}
