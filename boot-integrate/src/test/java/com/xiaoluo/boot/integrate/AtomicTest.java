package com.xiaoluo.boot.integrate;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import com.xiaoluo.boot.integrate.juc.AccessCounter;
import com.xiaoluo.boot.integrate.juc.LockCounter;
import com.xiaoluo.boot.integrate.juc.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @classname: com.xiaoluo.boot.integrate.AtomicTest
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/6/11 14:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AtomicTest {

    @Autowired
    AccessCounter accessCounter;
    @Autowired
    LockCounter lockCounter;

    @Test
    public void atomicCount() {
        ConcurrencyTester tester = ThreadUtil.concurrencyTest(10,()->{
            accessCounter.access();
            accessCounter.countAdd();
        });
        // 获取总的执行时间，单位毫秒
        Console.log(tester.getInterval());
        Console.log("final count: {}",accessCounter.getAccessCount());
        Console.log("final unsafe count: {}",accessCounter.getCount());
    }

    @Test
    public void lockCount() {
        ThreadUtil.concurrencyTest(100,()->{
            //lockCounter.lockAdd();
            //lockCounter.normalAdd();
            lockCounter.syncAdd();
        });
        ThreadUtil.sleep(2000);
        Console.log("final count: {}",lockCounter.getCount());
    }

    @Test
    public void copyOnWriteList(){
        List<Message> list = new CopyOnWriteArrayList<>();
        Message message = new Message("test1");
        list.add(message);
        Message message1 = list.get(0);
        Console.log(message1.hashCode());
        Console.log(message1.getMsg());
        message.setMsg("test2");
        Message message2 = list.get(0);
        Console.log(message2);
        Console.log(message2.hashCode());
        Console.log(message2.getMsg());
        Console.log(message1.equals(message2));
    }
}
