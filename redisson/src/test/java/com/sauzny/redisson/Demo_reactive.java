package com.sauzny.redisson;

import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.redisson.api.RSetReactive;

/**
 * *************************************************************************
 * @文件名称: Demo_reactive.java
 *
 * @包路径  : com.sauzny.redisson 
 *				 
 * @版权所有: Personal xinxin (C) 2017
 *
 * @类描述:   异步流式处理
 * 
 * @创建人:   ljx 
 *
 * @创建时间: 2018年1月3日 - 下午3:19:25 
 *	
 **************************************************************************
 */
public class Demo_reactive extends DemoBase{
    
    @Test
    public void foo(){
        RSetReactive<Object> set = clusterReactive.getSet("吉林");
        Publisher<Boolean> containPublisher = set.contains("15004925292");
        
        // java 1.8
        containPublisher.subscribe(new Subscriber<Boolean>() {

            @Override
            public void onSubscribe(Subscription s) {
                // TODO Auto-generated method stub
                s.request(1);
            }

            @Override
            public void onNext(Boolean t) {
                // TODO Auto-generated method stub
                System.out.println(t);
            }

            @Override
            public void onError(Throwable t) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void onComplete() {
                // TODO Auto-generated method stub
                
            }
        });
    }
}
