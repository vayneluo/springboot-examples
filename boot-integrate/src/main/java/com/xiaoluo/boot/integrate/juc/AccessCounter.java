package com.xiaoluo.boot.integrate.juc;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: AccessCounter
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/6/11 14:08
 */
@Service
public class AccessCounter {

    AtomicInteger accessCount = new AtomicInteger(0);

    int count;

    public void access(){
        accessCount.incrementAndGet();
    }

    public AtomicInteger getAccessCount() {
        return accessCount;
    }

    public void countAdd() {
       count++;
    }

    public int getCount() {
        return count;
    }
}
