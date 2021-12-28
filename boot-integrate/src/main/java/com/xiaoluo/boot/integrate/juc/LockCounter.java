package com.xiaoluo.boot.integrate.juc;

import cn.hutool.core.thread.ThreadUtil;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @classname: LockCounter
 * @description: 加锁实现线程安全
 * @author: Vayne.Luo
 * @date 2021/6/11 15:03
 */
@Service
public class LockCounter {

    private int count = 0;

    private ReentrantLock lock = new ReentrantLock();

    public void lockAdd(){
        lock.lock();
        try{
            add();
        }finally {
            lock.unlock();
        }
    }

    public void normalAdd(){
        System.out.println(Thread.currentThread().getName()+"----" + count);
        add();
    }

    public synchronized void syncAdd(){
        System.out.println(Thread.currentThread().getName()+"----" + count);
        add();
    }

    private void add(){
        count++;
    }

    public int getCount() {
        return count;
    }
}
