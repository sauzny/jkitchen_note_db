package com.sauzny.redisson;

import org.junit.Test;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;

/**
 * *************************************************************************
 * @文件名称: Demo_object.java
 *
 * @包路径  : com.sauzny.redisson 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   分布式对象
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月3日 - 下午5:20:01 
 *	
 **************************************************************************
 */
public class Demo_object extends DemoBase {
    

    /**
     * @描述: redisson 中 任何一个实例都可以使用getName获取实例名称（key）
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月3日 下午5:21:54
     */
    @Test
    public void foo_getName(){
        RMap<String, String> map = redisson.getMap("mymap");
        map.put("redisson", "redisson");
        System.out.println(map.getName()); // = mymap
    }
    
    /**
     * @描述: 关于keys的API
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月3日 下午5:39:55
     */
    @Test
    public void foo_keys(){
        
        RKeys keys = redisson.getKeys();

        Iterable<String> allKeys = keys.getKeys();
        Iterable<String> foundedKeys = keys.getKeysByPattern("key*");
        long numOfDeletedKeys = keys.delete("obj1", "obj2", "obj3");
        long deletedKeysAmount = keys.deleteByPattern("test?");
        String randomKey = keys.randomKey();
        long keysAmount = keys.count();

        allKeys.forEach(System.out::println);
        foundedKeys.forEach(System.out::println);
        System.out.println(numOfDeletedKeys);
        System.out.println(deletedKeysAmount);
        System.out.println(randomKey);
        System.out.println(keysAmount);
    }
}
