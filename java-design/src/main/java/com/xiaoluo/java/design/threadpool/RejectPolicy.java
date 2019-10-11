package com.xiaoluo.java.design.threadpool;

public interface RejectPolicy {

    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);
}