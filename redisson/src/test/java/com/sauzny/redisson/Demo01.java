package com.sauzny.redisson;

import org.redisson.api.RedissonClient;

import org.junit.Test;

public class Demo01 {

    @Test
    public void foo01(){
        RedissonClient redisson = CreateClientDemo.cluster();
        
        // redisson 不会自动关闭，需要显示的手动关闭
        redisson.shutdown();
    }
}
