package com.sauzny.redisson;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RCountDownLatch;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RPermitExpirableSemaphore;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RSemaphore;

/**
 * *************************************************************************
 * @文件名称: Demo_08.java
 *
 * @包路径  : com.sauzny.redisson 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   分布式锁和同步器
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月4日 - 下午3:38:21 
 *	
 **************************************************************************
 */
public class Demo_08 extends DemoBase {

    @Test
    public void foo_ReentrantLock() throws InterruptedException {

        // 支持自动过期解锁
        
        // RLock对象完全符合Java的Lock规范，
        // 也就是说只有拥有锁的进程才能解锁，其他进程解锁则会抛出IllegalMonitorStateException错误。
        // 但是如果遇到需要其他进程也能解锁的情况，请使用分布式信号量Semaphore 对象.
        
        RLock lock = redisson.getLock("anyLock");
        // 最常见的使用方法
        lock.lock();

        // 支持过期解锁功能
        // 10秒钟以后自动解锁
        // 无需调用unlock方法手动解锁
        lock.lock(10, TimeUnit.SECONDS);

        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        lock.unlock();
        
        
        // Redisson同时还为分布式锁提供了异步执行的相关方法
        
        lock.lockAsync();
        lock.lockAsync(10, TimeUnit.SECONDS);
        RFuture<Boolean> resAsync = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
    }
    
    @Test
    public void foo_FairLock() throws InterruptedException{
        
        // 在提供了自动过期解锁功能的同时，保证了当多个Redisson客户端线程同时请求加锁时，优先分配给先发出请求的线程。s
        
        RLock fairLock = redisson.getFairLock("anyLock");
        // 最常见的使用方法
        fairLock.lock();

        // 支持过期解锁功能
        // 10秒钟以后自动解锁
        // 无需调用unlock方法手动解锁
        fairLock.lock(10, TimeUnit.SECONDS);

        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
        fairLock.unlock();

        // Redisson同时还为分布式可重入公平锁提供了异步执行的相关方法：
        
        fairLock.lockAsync();
        fairLock.lockAsync(10, TimeUnit.SECONDS);
        RFuture<Boolean> resAsync = fairLock.tryLockAsync(100, 10, TimeUnit.SECONDS);
    }
    
    /**
     * @描述: 联锁
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:09:07
     */
    @Test
    public void foo_MultiLock(){
        
        // Redisson的RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。
        
        RLock lock1 = redissonInstance1.getLock("lock1");
        RLock lock2 = redissonInstance2.getLock("lock2");
        RLock lock3 = redissonInstance3.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 所有的锁都上锁成功才算成功。
        lock.lock();
        // do something
        lock.unlock();
    }
    
    /**
     * @描述: 红锁
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:20:45
     */
    @Test
    public void foo_RedLock(){
        
        // 实现了 reids 官方推荐的红锁
        
        RLock lock1 = redissonInstance1.getLock("lock1");
        RLock lock2 = redissonInstance2.getLock("lock2");
        RLock lock3 = redissonInstance3.getLock("lock3");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        // 同时加锁：lock1 lock2 lock3
        // 红锁在大部分节点上加锁成功就算成功。
        lock.lock();
        // do something
        lock.unlock();
    }
    
    /**
     * @描述: 读写锁
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:23:46
     */
    @Test
    public void foo_ReadWriteLock() throws InterruptedException{
        
        // wiki错误 
        // RReadWriteLock rwlock = redisson.getLock("anyRWLock");
        RReadWriteLock rwlock = redisson.getReadWriteLock("anyRWLock");
        // 最常见的使用方法
        rwlock.readLock().lock();
        // 或
        rwlock.writeLock().lock();

        // 支持过期解锁功能
        // 10秒钟以后自动解锁
        // 无需调用unlock方法手动解锁
        rwlock.readLock().lock(10, TimeUnit.SECONDS);
        // 或
        rwlock.writeLock().lock(10, TimeUnit.SECONDS);

        // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
        boolean resRead = rwlock.readLock().tryLock(100, 10, TimeUnit.SECONDS);
        // 或
        boolean resWrite = rwlock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
        // do something
        
        // wiki错误
        // lock.unlock();
    }
    
    /**
     * @描述: 信号量
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:24:46
     */
    @Test
    public void foo_Semaphore() throws InterruptedException{
        
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        semaphore.acquire();
        //或
        semaphore.acquireAsync();
        semaphore.acquire(23);
        semaphore.tryAcquire();
        //或
        semaphore.tryAcquireAsync();
        semaphore.tryAcquire(23, TimeUnit.SECONDS);
        //或
        semaphore.tryAcquireAsync(23, TimeUnit.SECONDS);
        semaphore.release(10);
        semaphore.release();
        //或
        semaphore.releaseAsync();
    }
    
    /**
     * @描述: 可过期性信号量
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:25:45
     */
    @Test
    public void foo_PermitExpirableSemaphore() throws InterruptedException{
        
        // 为每个信号增加了一个过期时间。
        // 每个信号可以通过独立的ID来辨识，释放时只能通过提交这个ID才能释放。
        
        RPermitExpirableSemaphore semaphore = redisson.getPermitExpirableSemaphore("mySemaphore");
        String permitId = semaphore.acquire();
        // 获取一个信号，有效期只有2秒钟。
        String permitId2 = semaphore.acquire(2, TimeUnit.SECONDS);
        // do something
        semaphore.release(permitId);
    }
    
    /**
     * @描述: 闭锁
     * @throws InterruptedException
     * @返回 void
     * @创建人  ljx 创建时间 2018年1月4日 下午4:27:01
     */
    @Test
    public void foo_CountDownLatch() throws InterruptedException{
        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
        latch.trySetCount(1);
        latch.await();

        // 在其他线程或其他JVM里
        RCountDownLatch latchAnother = redisson.getCountDownLatch("anyCountDownLatch");
        latchAnother.countDown();
    }
}
