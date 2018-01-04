package com.sauzny.redisson;

import org.junit.Test;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RSet;

/**
 * *************************************************************************
 * @文件名称: Demo_10.java
 *
 * @包路径  : com.sauzny.redisson 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   额外功能
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月4日 - 下午4:43:10 
 *	
 **************************************************************************
 */
public class Demo_10 extends DemoBase{
    
    /**
     * @描述: 复杂多维对象结构和对象引用的支持
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:42:57
     */
    @Test
    public void foo_() {
        
        /*
         * Redisson突破了Redis数据结构维度的限制，
         * 通过一个特殊引用对象的帮助，Redisson允许以任意的组合方式构建多维度的复杂对象结构，
         * 实现了对象之间的类似传统数据库里的关联关系。
         */
        
        // 范例中，一共创建了三个Redis数据结构：一个Redis HASH，一个Redis SET和一个Redis LIST。
        
        RMap<RSet<RList>, RList<RMap>> map = redisson.getMap("myMap");
        RSet<RList> set = redisson.getSet("mySet");
        RList<RMap> list = redisson.getList("myList");

        map.put(set, list);
        // 在特殊引用对象的帮助下，我们甚至可以构建一个循环引用，这是通过普通序列化方式实现不了的。
        set.add(list);
        list.add(map);
        
        // 在map包含的元素发生改变以后，无需再次“保存/持久”这些对象。因为map对象所记录的并不是序列化以后的值，而是元素对象的引用。
        // Redisson提供的对象在使用方法上，与普通Java对象的使用方法一致。从而让Redis成为内存的一部分，而不仅仅是一个储存空间。
    }
    
    /**
     * @描述: 命令的批量执行
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:42:01
     */
    @Test
    public void foo_batch(){
        
        // 在Redis中叫做管道。
        
        // 插入数据
        /*
        for(String city : citys){
            RSet<String> set = redisson.getSet(city);
            for(int i=0;i<RandomUtils.nextInt(1000, 10000);i++){
                set.add(RandomPersonUtils.getTel());
            }
        }
        */
        
        RBatch rBatch = cluster.createBatch();
        
        // 测试 redis集群下 batch操作的正确性
        rBatch.getSet("吉林").containsAsync("15004925292");
        rBatch.getSet("吉林").containsAsync("15004925292");
        rBatch.getSet("上海").containsAsync("15608238708");
        rBatch.getSet("上海").containsAsync("15608238708");
        rBatch.getSet("云南").containsAsync("15303568778");
        rBatch.getSet("云南").containsAsync("15303568778");
        
        /*
        BatchResult<?> batchResult = rBatch.execute();
        batchResult.forEach(System.out::println);
        */
        
        // BatchResult 实现了 List 接口
        
        RFuture<BatchResult<?>> rFuture = rBatch.executeAsync();
        rFuture.join().forEach(System.out::println);
        
        // 发送指令并等待执行，但是跳过结果
        //batch.executeSkipResult();
        // 或
        //batch.executeSkipResultAsync();
    }
}
