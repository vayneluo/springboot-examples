package com.xiaoluo.boot.integrate.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @classname: LastCommentTaskService
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/9/7 14:47
 */
@Service
@Slf4j
public class LastCommentTaskService {

    /** 流速控制 **/
    private RateLimiter rateLimiter = RateLimiter.create(1);

    public void dealLastCommentTask(){
        for (int i = 0; i < 100; i++) {
            batchUpdateLastComment(i);
        }
    }

    private void batchUpdateLastComment(int i) {
        rateLimiter.acquire();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Current Batch: {} ,,, Current Time : {}", i,LocalDateTime.now());
    }
}
