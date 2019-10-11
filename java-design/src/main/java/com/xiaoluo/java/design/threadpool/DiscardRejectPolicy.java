package com.xiaoluo.java.design.threadpool;

import lombok.extern.slf4j.Slf4j;

/**
 * @classname: DiscardRejectPolicy
 * @description: 拒绝策略
 * @author: Vayne.Luo
 * @date 2019/10/11 09:48
 */
@Slf4j
public class DiscardRejectPolicy implements RejectPolicy {
    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        log.info("discard one task ");
    }
}
