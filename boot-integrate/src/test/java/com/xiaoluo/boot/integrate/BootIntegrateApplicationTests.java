package com.xiaoluo.boot.integrate;

import com.xiaoluo.boot.integrate.guava.GuavaCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootIntegrateApplicationTests {

    @Autowired
    GuavaCacheService cacheService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testCache(){
        List<String> listCache = cacheService.getListCache();
        System.out.println(listCache);
        try {
            Thread.sleep(2000);
            List<String> listCache1 = cacheService.getListCache();
            System.out.println(listCache1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
