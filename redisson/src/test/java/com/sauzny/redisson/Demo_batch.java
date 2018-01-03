package com.sauzny.redisson;

import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RSet;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class Demo_batch extends DemoBase{
    
    @Test
    public void foo_batch(){
        
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
