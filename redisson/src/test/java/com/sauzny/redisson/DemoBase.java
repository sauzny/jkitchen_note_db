package com.sauzny.redisson;

import org.junit.After;
import org.junit.Before;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;

public class DemoBase {
    
    // 这个测试数据里，河北前面有个空格
    // 我不是故意加个空格的，我发现的时候，我已经插入了一些测试数据，我就不改了
    
    public static final String[] citys = new String[]{"辽宁","吉林","黑龙江"," 河北","山西","陕西","山东","安徽","江苏","浙江","河南","湖北","湖南","江西","台湾","福建","云南","海南","四川","贵州","广东","甘肃","青海","西藏","新疆","广西","内蒙古","宁夏","北京","天津","上海","重庆"};
    
    RedissonClient cluster;
    
    // 异步流式处理，Demo_reactive.java
    /**
     * 作为一个热门的新标准，基于Reactor项目的异步流处理标准被添加到Java 9的语言规范里。
     * 在Java 9正式发布以前，Redisson提前提供了满足该标准的程序接口。
     * 所有Redisson异步流对象都可以通过一个单独的RedissonReactiveClient接口来获取。
     */
    RedissonReactiveClient clusterReactive;
    
    // 默认连接 127.0.0.1 6379
    RedissonClient redisson = Redisson.create();
    
    @Before
    public void before(){
        this.cluster = CreateClientDemo.cluster();
        this.clusterReactive = CreateClientDemo.clusterReactive();
    }
    
    @After
    public void after(){
        // redisson 不会自动关闭，需要显示的手动关闭
        this.cluster.shutdown();
        this.clusterReactive.shutdown();
    }
}
