package com.sauzny.redisson;

import org.apache.commons.lang3.RandomUtils;
import org.redisson.api.BatchResult;
import org.redisson.api.RBatch;
import org.redisson.api.RFuture;
import org.redisson.api.RSet;
import org.redisson.api.RSetAsync;
import org.redisson.api.RedissonClient;


public class Test {

    public static final String[] citys = new String[]{"辽宁","吉林","黑龙江"," 河北","山西","陕西","山东","安徽","江苏","浙江","河南","湖北","湖南","江西","台湾","福建","云南","海南","四川","贵州","广东","甘肃","青海","西藏","新疆","广西","内蒙古","宁夏","北京","天津","上海","重庆"};
    
    public static void addSet(){
        RedissonClient redisson = CreateClientDemo.cluster();
        for(String city : citys){
            RSet<String> set = redisson.getSet(city);
            for(int i=0;i<RandomUtils.nextInt(1000, 10000);i++){
                set.add(RandomPersonUtils.getTel());
            }
        }
    }
    
    public static void check(){

        RedissonClient redisson = CreateClientDemo.cluster();
        
        RBatch rBatch = redisson.createBatch();
        
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
        
        RFuture<BatchResult<?>> rFuture = rBatch.executeAsync();
        rFuture.join().forEach(System.out::println);
    }
    
    public static void main(String[] args) {
        Test.check();
    }
}
