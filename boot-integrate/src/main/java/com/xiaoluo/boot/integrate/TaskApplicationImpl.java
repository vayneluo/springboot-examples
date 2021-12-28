package com.xiaoluo.boot.integrate;

import com.xiaoluo.boot.integrate.guava.LastCommentTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import java.time.LocalDateTime;

/**
 * @classname: TaskApplicationImpl
 * @description: 项目启动执行
 * @author: Vayne.Luo
 * @date 2020/9/7 14:44
 */
@Slf4j
//@Component
public class TaskApplicationImpl implements ApplicationRunner {

    @Autowired
    LastCommentTaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("------------------批处理任务启动------------------ 开始时间:{}", LocalDateTime.now());
        taskService.dealLastCommentTask();
        log.info("------------------批处理任务结束------------------ 结束时间:{}", LocalDateTime.now());
    }
}
