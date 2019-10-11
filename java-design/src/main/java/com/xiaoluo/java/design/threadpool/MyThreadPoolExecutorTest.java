package com.xiaoluo.java.design.threadpool;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @classname: MyThreadPoolExecutorTest
 * @description: 线程池测试
 * @author: Vayne.Luo
 * @date 2019/10/11 10:55
 */
public class MyThreadPoolExecutorTest {

    public static void main(String[] args) {
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor("test", 5, 10,
                new ArrayBlockingQueue<>(15),
                new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                try{
                    System.out.println("current Thread name : " + Thread.currentThread().getName() + "开始执行");
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName()+"执行完毕" + "----------"+ "running: "+ LocalDateTime.now() + ": " + num.incrementAndGet());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程池中线程数目："+executor.getRunningCount() +"，队列中等待执行的任务数目："+
                    executor.getTaskQueue().size());
        }
    }
}
