package com.xiaoluo.java.design;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import com.xiaoluo.java.design.threadpool.AccessCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @classname: AtomicTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/6/11 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AtomicTest {

    @Autowired
    AccessCounter accessCounter;

    @Test
    public void contextLoads() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(10,()->{
            accessCounter.access();
        });
        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
        Console.log("final count: {}",accessCounter.getAccessCount());

    }
}
