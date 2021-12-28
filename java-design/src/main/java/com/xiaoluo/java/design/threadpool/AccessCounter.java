package com.xiaoluo.java.design.threadpool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
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

    public void access(){
        accessCount.incrementAndGet();
    }

    public AtomicInteger getAccessCount() {
        return accessCount;
    }
}
