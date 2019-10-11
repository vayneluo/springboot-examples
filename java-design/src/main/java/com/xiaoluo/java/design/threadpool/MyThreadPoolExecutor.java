package com.xiaoluo.java.design.threadpool;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: MyThreadPoolExecutor
 * @description: 手写线程池
 * @author: Vayne.Luo
 * @date 2019/10/11 09:18
 */
@Data
@Slf4j
public class MyThreadPoolExecutor implements Executor {
    /** 线程名称 **/
    private String name;
    /** 线程序列号 **/
    private AtomicInteger sequence = new AtomicInteger(0);
    /** 当前运行的线程数 **/
    private AtomicInteger runningCount = new AtomicInteger(0);
    /** 核心线程数 **/
    private int coreSize;
    /** 最大线程数 **/
    private int maxSize;
    /** 阻塞队列 **/
    private BlockingQueue<Runnable> taskQueue;
    /** 拒绝策略 **/
    private RejectPolicy rejectPolicy;

    public MyThreadPoolExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        this.name = name;
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = taskQueue;
        this.rejectPolicy = rejectPolicy;
    }

    @Override
    public void execute(Runnable task) {
        int count = runningCount.get();
        if(count < coreSize){
            if(addWorker(task,true)){
                return;
            }
        }

        // 添加核心线程失败，执行任务入队
        if(taskQueue.offer(task)){
            // do nothing
        }else {
            // 入队失败，队列满了，尝试添加非核心线程
            if(!addWorker(task,false)){
                // 添加非核心线程失败，执行拒绝策略
                rejectPolicy.reject(task,this);
            }
        }
    }

    /**
     * @description: 添加到线程池
     * @param: [task, core] 当前任务，是否核心线程
     * @author: Vayne.Luo
     * @date: 2019/10/11 9:56
     */
    private boolean addWorker(Runnable task, boolean core) {
        for (;;){
            // 正在运行的线程数
            int count = runningCount.get();
            // 核心线程还是最大线程数
            int max = core ? coreSize : maxSize;
            if(count >= max){
                return false;
            }
            if(runningCount.compareAndSet(count,count + 1)){
                String threadName = (core ? "core_" : "") + name +sequence.incrementAndGet();
                new Thread(() ->{
                    log.info("thread name : {}",Thread.currentThread().getName());
                    Runnable runnable = task;
                    while(null != runnable || ((runnable = getTask()) != null)){
                        try{
                            runnable.run();
                        }finally {
                            runnable = null;
                        }
                    }
                },threadName).start();
                break;
            }
        }
        return true;
    }

    private Runnable getTask() {
        try {
            // take()方法会一直阻塞直到取到任务为止
            return taskQueue.take();
        } catch (InterruptedException e) {
            // 线程中断了，返回null可以结束当前线程
            // 当前线程都要结束了，理应要把runningCount的数量减一
            runningCount.decrementAndGet();
            return null;
        }
    }
}
