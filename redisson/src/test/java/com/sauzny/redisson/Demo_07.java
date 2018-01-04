package com.sauzny.redisson;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.LocalCachedMapOptions.EvictionPolicy;
import org.redisson.api.LocalCachedMapOptions.ReconnectionStrategy;
import org.redisson.api.RBlockingDeque;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RBoundedBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RDeque;
import org.redisson.api.RFuture;
import org.redisson.api.RLexSortedSet;
import org.redisson.api.RList;
import org.redisson.api.RListMultimap;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;
import org.redisson.api.RPriorityDeque;
import org.redisson.api.RPriorityQueue;
import org.redisson.api.RQueue;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSet;
import org.redisson.api.RSetCache;
import org.redisson.api.RSetMultimap;
import org.redisson.api.RSetMultimapCache;
import org.redisson.api.RSortedSet;

import com.sauzny.redisson.entity.SimpleKey;
import com.sauzny.redisson.entity.SimpleValue;
import com.sauzny.redisson.entity.SomeObject;

/**
 * *************************************************************************
 * @文件名称: Demo_07.java
 *
 * @包路径  : com.sauzny.redisson 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   分布式集合
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月4日 - 上午11:07:25 
 *	
 **************************************************************************
 */
public class Demo_07 extends DemoBase {

    /**
     * @描述: Map
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:12:23
     */
    @Test
    public void foo_Map(){
        // 保持了元素的插入顺序。该对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        RMap<String, SomeObject> map = redisson.getMap("anyMap");
        SomeObject prevObject = map.put("123", new SomeObject());
        SomeObject currentObject = map.putIfAbsent("323", new SomeObject());
        SomeObject obj = map.remove("123");

        map.fastPut("321", new SomeObject());
        map.fastRemove("321");
        // wiki错误
        // Future<SomeObject> putAsyncFuture = map.putAsync("321");
        // Future<Void> fastPutAsyncFuture = map.fastPutAsync("321");
        RFuture<SomeObject> putAsyncFuture = map.putAsync("321", new SomeObject());
        RFuture<Boolean> fastPutAsyncFuture = map.fastPutAsync("321", new SomeObject());

        map.fastPutAsync("321", new SomeObject());
        map.fastRemoveAsync("321");
        
        // map 的 删除机制
        
        /*
         * 单个元素的淘汰机制。同时仍然保留了元素的插入顺序。
         * 目前的Redis自身并不支持哈希（Hash）当中的元素淘汰，通过org.redisson.EvictionScheduler实例来实现定期清理的
         * 每次运行最多清理100个过期元素。
         * 任务的启动时间将根据上次实际清理数量自动调整，间隔时间在1秒到2小时之间。
         * 该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
         */
        
        RMapCache<String, SomeObject> mapCache = redisson.getMapCache("anyMap");
        // 有效时间 ttl = 10分钟
        mapCache.put("key1", new SomeObject(), 10, TimeUnit.MINUTES);
        // 有效时间 ttl = 10分钟, 最长闲置时间 maxIdleTime = 10秒钟
        mapCache.put("key1", new SomeObject(), 10, TimeUnit.MINUTES, 10, TimeUnit.SECONDS);

        // 有效时间 = 3 秒钟
        mapCache.putIfAbsent("key2", new SomeObject(), 3, TimeUnit.SECONDS);
        // 有效时间 ttl = 40秒钟, 最长闲置时间 maxIdleTime = 10秒钟
        mapCache.putIfAbsent("key2", new SomeObject(), 40, TimeUnit.SECONDS, 10, TimeUnit.SECONDS);
        
        // map 的 本地缓存
        
        /*
         * 内部有一个evictionScheduler
         * 高度频繁的读取操作让网络通信都被视为瓶颈的情况时，使用Redisson提供的分布式Map本地缓存RLocalCachedMapJava对象会是一个很好的选择。
         */
        
        LocalCachedMapOptions options = LocalCachedMapOptions.defaults()
                // 淘汰机制有LFU, LRU和NONE这几种算法策略可供选择
               .evictionPolicy(EvictionPolicy.LFU)
               .cacheSize(1000)
                // 如果该值是`真(true)`时，在该实例执行更新和删除操作的同时，将向其他所有的相同实例发
                // 送针对该元素的淘汰消息。其他相同实例在收到该消息以后，会同时删除自身的缓存。下次读取
                // 该元素时会从Redis服务器获取。
               // wiki过期
               //.invalidateEntryOnChange(false)
               // 如果映射实例暂时断开，则清除本地缓存。
               // clear 是默认值
               .reconnectionStrategy(ReconnectionStrategy.CLEAR)
                // 每个Map本地缓存里元素的有效时间，默认毫秒为单位
               .timeToLive(10000)
                // 或者
               .timeToLive(10, TimeUnit.SECONDS)
                // 每个Map本地缓存里元素的最长闲置时间，默认毫秒为单位
               .maxIdle(10000)
                // 或者
               .maxIdle(10, TimeUnit.SECONDS);
          RLocalCachedMap<String, Integer> localCachedMap = redisson.getLocalCachedMap("test", options);

          localCachedMap.put("1", 1);
          localCachedMap.put("2", 2);

          localCachedMap.fastPut("3", 4);
          
          // 当不再使用Map本地缓存对象的时候应该自行手动销毁
          // 如果Redisson对象被关闭（shutdown）了，则不用手动销毁。
          localCachedMap.destroy();
          
          
          /*
           * RClusteredMap 数据分片
           * 该功能仅限于Redisson PRO版本。
           */
         
    }
    
    /**
     * @描述: 多值映射
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:12:43
     */
    @Test
    public void foo_Multimap(){
        // Redisson的分布式RMultimap Java对象允许Map中的一个字段值包含多个元素。 
        // 字段总数受Redis限制，每个Multimap最多允许有4 294 967 295个不同字段。
        
        // 基于Set的Multimap不允许一个字段值包含有重复的元素。
        
        RSetMultimap<SimpleKey, SimpleValue> mapSet = redisson.getSetMultimap("myMultimap");
        mapSet.put(new SimpleKey("0"), new SimpleValue("1"));
        mapSet.put(new SimpleKey("0"), new SimpleValue("2"));
        mapSet.put(new SimpleKey("3"), new SimpleValue("4"));

        Set<SimpleValue> allValuesSet = mapSet.get(new SimpleKey("0"));

        List<SimpleValue> newValuesSet = Arrays.asList(new SimpleValue("7"), new SimpleValue("6"), new SimpleValue("5"));
        Set<SimpleValue> oldValuesSet = mapSet.replaceValues(new SimpleKey("0"), newValuesSet);

        Set<SimpleValue> removedValuesSet = mapSet.removeAll(new SimpleKey("0"));
     
        // 基于List的Multimap在保持插入顺序的同时允许一个字段下包含重复的元素。
        
        RListMultimap<SimpleKey, SimpleValue> mapList = redisson.getListMultimap("test1");
        mapList.put(new SimpleKey("0"), new SimpleValue("1"));
        mapList.put(new SimpleKey("0"), new SimpleValue("2"));
        mapList.put(new SimpleKey("0"), new SimpleValue("1"));
        mapList.put(new SimpleKey("3"), new SimpleValue("4"));

        List<SimpleValue> allValuesList = mapList.get(new SimpleKey("0"));

        Collection<SimpleValue> newValuesList = Arrays.asList(new SimpleValue("7"), new SimpleValue("6"), new SimpleValue("5"));
        List<SimpleValue> oldValuesList = mapList.replaceValues(new SimpleKey("0"), newValuesList);

        List<SimpleValue> removedValuesList = mapList.removeAll(new SimpleKey("0"));
        
        // Multimap 淘汰机制
        
        /*
         * 分别对应的是Set和List的Multimaps。
         * 通过org.redisson.EvictionScheduler实例来实现定期清理的
         * 每次运行最多清理100个过期元素。
         * 任务的启动时间将根据上次实际清理数量自动调整，间隔时间在1秒到2小时之间。
         * 该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
         */
        
        RSetMultimapCache<String, String> multimapCache = redisson.getSetMultimapCache("myMultimap");
        multimapCache.put("1", "a");
        multimapCache.put("1", "b");
        multimapCache.put("1", "c");

        multimapCache.put("2", "e");
        multimapCache.put("2", "f");

        multimapCache.expireKey("2", 10, TimeUnit.MINUTES);
    }
    
    /**
     * @描述: 集
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:13:00
     */
    @Test
    public void foo_Set(){
        
        // 该对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RSet<SomeObject> set = redisson.getSet("anySet");
        set.add(new SomeObject());
        set.remove(new SomeObject());
        
        //Redisson PRO版本中的Set对象还可以在Redis集群环境下支持单集合数据分片。
        
        // Set 淘汰机制
        
        /*
         * 目前的Redis自身并不支持Set当中的元素淘汰，通过org.redisson.EvictionScheduler实例来实现定期清理的
         * 每次运行最多清理100个过期元素。
         * 任务的启动时间将根据上次实际清理数量自动调整，间隔时间在1秒到2小时之间。
         * 该次清理数量少于上次清理数量，时间间隔将增加1.5倍。
         */
        
        RSetCache<SomeObject> setCache = redisson.getSetCache("anySet");
        // ttl = 10 seconds
        setCache.add(new SomeObject(), 10, TimeUnit.SECONDS);
        
        /*
         * RClusteredSet 数据分片
         * 该功能仅限于Redisson PRO版本。
         */
        
    }
    
    /**
     * @描述: 有序集
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:13:15
     */
    @Test
    public void foo_SortedSet(){
        // 在保证元素唯一性的前提下，通过比较器（Comparator）接口实现了对元素的排序
        
        RSortedSet<Integer> setSorted = redisson.getSortedSet("anySet");
        setSorted.trySetComparator((i1,i2) -> i1-i2); // 配置元素比较器
        setSorted.add(3);
        setSorted.add(1);
        setSorted.add(2);

        setSorted.removeAsync(0);
        setSorted.addAsync(5);
    }

    /**
     * @描述: 计分排序集
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:13:23
     */
    @Test
    public void foo_ScoredSortedSet(){
        
        // 在保证元素唯一性的前提下，可以按插入时指定的元素评分排序的集合
        
        RScoredSortedSet<SomeObject> setScoredSorted = redisson.getScoredSortedSet("simple");

        setScoredSorted.add(0.13, new SomeObject("a", "b"));
        setScoredSorted.addAsync(0.251, new SomeObject("c", "d"));
        setScoredSorted.add(0.302, new SomeObject("g", "d"));

        setScoredSorted.pollFirst();
        setScoredSorted.pollLast();

        int index = setScoredSorted.rank(new SomeObject("g", "d")); // 获取元素在集合中的位置
        Double score = setScoredSorted.getScore(new SomeObject("g", "d")); // 获取元素的评分
    }
    
    /**
     * @描述: 字典排序集
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:13:33
     */
    @Test
    public void foo_LexSortedSet(){

        // wiki错误，描述中出现了笔误
        // 在保证元素唯一性的前提下，将其中的所有字符串元素按照字典顺序排列。
        
        RLexSortedSet set = redisson.getLexSortedSet("simple");
        set.add("d");
        set.add("f");
        set.addAsync("e");
        
        // wiki错误
        // set.lexRangeTail("d", false);
        // set.lexCountHead("e");
        // set.lexRange("d", true, "z", false);
        

        // 参数是fromElement-toElement，其中boolean类型标识，是否包含
        Collection<String> range = set.range("d", true, "e", false);
        // Head无限制，参数是 toElement
        Collection<String> rangeHead = set.rangeHead("e", true);
        // 参数是 fromElement，Tail无限制
        Collection<String> rangeTail = set.rangeTail("e", true);

        int countHead = set.countHead("d", true);

        System.out.println(range);
        System.out.println(rangeHead);
        System.out.println(rangeTail);
        System.out.println(countHead);
        
    }
    
    /**
     * @throws InterruptedException 
     * @描述: 列表
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:14:46
     */
    @Test
    public void foo_List() throws InterruptedException{
        
        // 该对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RList<SomeObject> list = redisson.getList("anyList");
        list.add(new SomeObject());
        list.get(0);
        list.remove(new SomeObject());
        
        // 我在这使用了另一个客户端 向"stringlist"中增加元素，测试效果
        // 结果：我在另一个客户端增加元素之后，这边的输出会响应的产生变化
        RList<String> list1 = redisson.getList("stringlist");
        list1.add("a");
        for(int i=0;i<10;i++){
            Thread.sleep(6000L);
            System.out.println(list1.get(0));
            System.out.println(list1.size());
        }
    }
    
    /**
     * @描述: 队列
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:14:53
     */
    @Test
    public void foo_Queue(){
        // wiki错误 描述错误
        // 尽管RQueue对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RQueue<SomeObject> queue = redisson.getQueue("anyQueue");
        queue.add(new SomeObject());
        SomeObject obj = queue.peek();
        SomeObject someObj = queue.poll();
    }
    
    /**
     * @描述: 双端队列
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:15:10
     */
    @Test
    public void foo_Deque(){
        
        // 尽管RDeque对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RDeque<SomeObject> queue = redisson.getDeque("anyDeque");
        queue.addFirst(new SomeObject());
        queue.addLast(new SomeObject());
        SomeObject obj = queue.removeFirst();
        SomeObject someObj = queue.removeLast();
    }
    
    /**
     * @描述: 阻塞队列
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:15:48
     */
    @Test
    public void foo_BlockingQueue() throws InterruptedException{
        
        // 尽管RBlockingQueue对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RBlockingQueue<SomeObject> queue = redisson.getBlockingQueue("anyQueue");
        queue.offer(new SomeObject());

        SomeObject obj = queue.peek();
        SomeObject someObj = queue.poll();
        SomeObject ob = queue.poll(10, TimeUnit.MINUTES);
        
        // poll, pollFromAny, pollLastAndOfferFirstTo和take方法内部采用话题订阅发布实现
        // 在Redis节点故障转移（主从切换）或断线重连以后
        // 内置的相关话题监听器将自动完成话题的重新订阅
    }
    
    /**
     * @描述: 有界阻塞列队
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:15:55
     */
    @Test
    public void foo_BoundedBlockingQueue() throws InterruptedException {
        
        // 对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        // 列队的初始容量（边界）必须在使用前设定好。
        
        RBoundedBlockingQueue<SomeObject> queue = redisson.getBoundedBlockingQueue("anyQueue");
        // 如果初始容量（边界）设定成功则返回`真（true）`，
        // 如果初始容量（边界）已近存在则返回`假（false）`。
        queue.trySetCapacity(2);

        queue.offer(new SomeObject(1));
        queue.offer(new SomeObject(2));
        // 此时容量已满，下面代码将会被阻塞，直到有空闲为止。
        queue.put(new SomeObject());

        SomeObject obj = queue.peek();
        SomeObject someObj = queue.poll();
        SomeObject ob = queue.poll(10, TimeUnit.MINUTES);

        // poll, pollFromAny, pollLastAndOfferFirstTo和take方法内部采用话题订阅发布实现
        // 在Redis节点故障转移（主从切换）或断线重连以后
        // 内置的相关话题监听器将自动完成话题的重新订阅
        
    }
    
    /**
     * @描述: 阻塞双端列队
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:11:26
     */
    @Test
    public void foo_BlockingDeque() throws InterruptedException{
        
        // 尽管RBlockingDeque对象无初始大小（边界）限制，但对象的最大容量受Redis限制，最大元素数量是4 294 967 295个。
        
        RBlockingDeque<Integer> deque = redisson.getBlockingDeque("anyDeque");
        deque.putFirst(1);
        deque.putLast(2);
        // wiki 错误
        // Integer firstValue = queue.takeFirst();
        // Integer lastValue = queue.takeLast();
        // Integer firstValue = queue.pollFirst(10, TimeUnit.MINUTES);
        // Integer lastValue = queue.pollLast(3, TimeUnit.MINUTES);
        
        Integer firstValue = deque.takeFirst();
        Integer lastValue = deque.takeLast();
        Integer firstValue1 = deque.pollFirst(10, TimeUnit.MINUTES);
        Integer lastValue1 = deque.pollLast(3, TimeUnit.MINUTES);
        
        // poll, pollFromAny, pollLastAndOfferFirstTo和take方法内部采用话题订阅发布实现
        // 在Redis节点故障转移（主从切换）或断线重连以后
        // 内置的相关话题监听器将自动完成话题的重新订阅
    }
    
    /**
     * @描述: 阻塞公平列队
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:21:41
     */
    @Test
    public void foo_BlockingFairQueue(){
        
        // 保证了poll和take方法获取消息的先入顺序
        // 让列队里的消息被均匀的发布到处在复杂分布式环境中的各个处理节点里。
        
        // wiki错误，api里没有这个方法???
        
        /*
        RBlockingFairQueue queue = redisson.getBlockingFairQueue("myQueue");
        queue.offer(new SomeObject());

        SomeObject obj = queue.peek();
        SomeObject someObj = queue.poll();
        SomeObject ob = queue.poll(10, TimeUnit.MINUTES);
        */
    }
    
    /**
     * @描述: 延迟列队
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:21:50
     */
    @Test
    public void foo_DelayedQueue(){
        
        // 该功能可以用来实现消息传送延迟按几何增长或几何衰减的发送策略。
        
        RQueue<String> distinationQueue = redisson.getQueue("anyQueue");
        RDelayedQueue<String> delayedQueue = redisson.getDelayedQueue(distinationQueue);
        // 10秒钟以后将消息发送到指定列队
        delayedQueue.offer("msg1", 10, TimeUnit.SECONDS);
        // 一分钟以后将消息发送到指定列队
        delayedQueue.offer("msg2", 1, TimeUnit.MINUTES);
        
        //在该对象不再需要的情况下，应该主动销毁。仅在相关的Redisson对象也需要关闭的时候可以不用主动销毁。
        delayedQueue.destroy();
    }
    
    /**
     * @描述: 优先队列
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:24:09
     */
    @Test
    public void foo_PriorityQueue(){
        
        // 可以通过比较器（Comparator）接口来对元素排序。
        
        RPriorityQueue<Integer> queue = redisson.getPriorityQueue("anyQueue");
        queue.trySetComparator((i1,i2) -> i1-i2); // 指定对象比较器
        queue.add(3);
        queue.add(1);
        queue.add(2);
        
        // wiki错误
        // queue.removeAsync(0);
        // queue.addAsync(5);

        queue.poll();
    }
    
    /**
     * @描述: 优先双端队列
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午3:24:18
     */
    @Test
    public void foo_PriorityDeque(){
        
        // 可以通过比较器（Comparator）接口来对元素排序。
        
        RPriorityDeque<Integer> queue = redisson.getPriorityDeque("anyQueue");
        queue.trySetComparator((i1,i2) -> i1-i2); // 指定对象比较器
        queue.addLast(3);
        queue.addFirst(1);
        queue.add(2);

        // wiki错误
        //queue.removeAsync(0);
        //queue.addAsync(5);

        queue.pollFirst();
        queue.pollLast();
    }
}
