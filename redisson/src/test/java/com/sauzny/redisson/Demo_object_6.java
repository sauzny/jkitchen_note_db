package com.sauzny.redisson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.redisson.api.GeoEntry;
import org.redisson.api.GeoPosition;
import org.redisson.api.GeoUnit;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBinaryStream;
import org.redisson.api.RBitSet;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RBucket;
import org.redisson.api.RFuture;
import org.redisson.api.RGeo;
import org.redisson.api.RHyperLogLog;
import org.redisson.api.RKeys;
import org.redisson.api.RMap;
import org.redisson.api.RPatternTopic;
import org.redisson.api.RTopic;
import org.redisson.api.listener.MessageListener;
import org.redisson.api.listener.PatternMessageListener;
import org.redisson.client.protocol.pubsub.Message;

import com.sauzny.redisson.entity.Student;

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
public class Demo_object_6 extends DemoBase {
    

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
    
    @Test
    public void foo_ObjectBucket(){
        
        // Redisson的分布式RBucketJava对象是一种通用对象桶可以用来存放任类型的对象。
        RBucket<Student> bucket = redisson.getBucket("student");
        
        bucket.set(new Student(1)); 
        System.out.println(bucket.get());
        
        bucket.trySet(new Student(3));
        System.out.println(bucket.get());
        
        bucket.compareAndSet(new Student(4), new Student(5));
        System.out.println(bucket.get());

        bucket.compareAndSet(new Student(1), new Student(5));
        System.out.println(bucket.get());
        
        bucket.getAndSet(new Student(6));
        System.out.println(bucket.get());
    }
    
    @Test
    public void foo_BinaryStream() throws IOException{
        
        String str = "anyStream";
        
        RBinaryStream stream = redisson.getBinaryStream("anyStream");
        byte[] content = str.getBytes(StandardCharsets.UTF_8);
        stream.set(content);

        InputStream is = stream.getInputStream();
        byte[] readBuffer = new byte[512];
        is.read(readBuffer);

        System.out.println(new String(stream.get(), StandardCharsets.UTF_8));
        
        String newStr = "_newAnyStream";
        
        OutputStream os = stream.getOutputStream();
        byte[] contentToWrite = newStr.getBytes(StandardCharsets.UTF_8);
        os.write(contentToWrite);
        
        System.out.println(new String(stream.get(), StandardCharsets.UTF_8));
    }
    
    @Test
    public void foo_GeospatialBucket(){
        RGeo<String> geo = redisson.getGeo("testGeo");

        // 经度-纬度-地点名字
        geo.add(new GeoEntry(13.361389, 38.115556, "Palermo"),
                new GeoEntry(15.087269, 37.502669, "Catania"));
        geo.addAsync(37.618423, 55.751244, "Moscow");

        // 两点间的距离
        Double distance = geo.dist("Palermo", "Catania", GeoUnit.METERS);
        System.out.println(distance);
        
        // 将GeoEntry实例hash成 11个长度的字符串
        RFuture<Map<String, String>> rFuture = geo.hashAsync("Palermo", "Catania");
        System.out.println(rFuture.join());
        
        // 获取地点信息
        Map<String, GeoPosition> positions = geo.pos("test2", "Palermo", "test3", "Catania", "test1");
        System.out.println(positions);
        
        // 列出在radius（半径）范围内的城市的名字
        List<String> cities = geo.radius(15, 37, 200, GeoUnit.KILOMETERS);
        System.out.println(cities);
        
        // 列出在radius（半径）范围内的城市的  经度-纬度-地点名字
        Map<String, GeoPosition> citiesWithPositions = geo.radiusWithPosition(15, 37, 200, GeoUnit.KILOMETERS);
        System.out.println(citiesWithPositions);
    }
    
    @Test
    public void foo_BitSet(){
        // 最大长度为      4 294 967 295。
        RBitSet set = redisson.getBitSet("simpleBitset");
        set.set(0, true);
        set.set(1812, false);
        set.clear(0);
        // wiki 有这个代码，但是这个好像不对吧？？
        // set.addAsync("e");
        set.xor("anotherBitset");
    }
    
    @Test
    public void foo_AtomicLong(){
        RAtomicLong atomicLong = redisson.getAtomicLong("myAtomicLong");
        atomicLong.set(3);
        System.out.println(atomicLong.get());
        atomicLong.incrementAndGet();
        System.out.println(atomicLong.get());
    }
    
    @Test
    public void foo_AtomicDouble(){
        RAtomicDouble atomicDouble = redisson.getAtomicDouble("myAtomicDouble");
        atomicDouble.set(2.81);
        System.out.println(atomicDouble.get());
        atomicDouble.addAndGet(4.11);
        atomicDouble.get();
        System.out.println(atomicDouble.get());
    }
    
    @Test
    public void foo_Topic(){
        
        // 在Redis节点故障转移（主从切换）或断线重连以后，所有的话题监听器将自动完成话题的重新订阅。
        
        RTopic<Student> topic = redisson.getTopic("anyTopic");
        
        /*
        topic.addListener(new MessageListener<Student>() {
            @Override
            public void onMessage(String channel, Student message) {
                //...
            }
        });
         */
        
        // lambda表达式写法
        topic.addListener( (channel, message) -> System.out.println("channel：" + channel + " message：" + message) );
        
        // 在其他线程或JVM节点
        RTopic<Student> topic1 = redisson.getTopic("anyTopic");
        long clientsReceivedMessage = topic1.publish(new Student());
        
     // 订阅所有满足`topic2.*`表达式的话题
        RPatternTopic<Message> topic2 = redisson.getPatternTopic("topic2.*");
        int listenerId = topic2.addListener(new PatternMessageListener<Message>() {
            @Override
            public void onMessage(String pattern, String channel, Message msg) {
                 Assert.fail();
            }
        });
    }
    
    @Test
    public void foo_BloomFilter() {
        RBloomFilter<Student> bloomFilter = redisson.getBloomFilter("sample");
        // 初始化布隆过滤器，预计统计元素数量为55000000，期望误差率为0.03
        bloomFilter.tryInit(55000000L, 0.03);
        bloomFilter.add(new Student("field1Value", "field2Value"));
        bloomFilter.add(new Student("field5Value", "field8Value"));
        boolean isResult1 =  bloomFilter.contains(new Student("field1Value", "field8Value"));
        System.out.println(isResult1);
        boolean isResult2 =  bloomFilter.contains(new Student("field1Value", "field2Value"));
        System.out.println(isResult2);
        boolean isResult3 =  bloomFilter.contains(new Student("field5Value", "field8Value"));
        System.out.println(isResult3);
    }
    
    @Test
    public void foo_HyperLogLog(){
        RHyperLogLog<Integer> log = redisson.getHyperLogLog("log");
        log.add(1);
        log.add(2);
        log.add(3);
        System.out.println(log.count());
        
        log.add(3);
        System.out.println(log.count());
    }
}
