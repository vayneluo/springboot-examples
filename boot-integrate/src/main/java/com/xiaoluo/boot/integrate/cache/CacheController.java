package com.xiaoluo.boot.integrate.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @classname: CacheController
 * @description: 缓存
 * @author: Vayne.Luo
 * @date 2020/11/19 11:50
 */
@RestController
@RequestMapping
public class CacheController {

    @Cacheable("testCache")
    @GetMapping(value = "/hello/{name}")
    public String sayHello(@PathVariable String name){
        System.out.println("无缓存进入");
        System.out.println("访问时间"+ LocalDateTime.now());
        return name;
    }
}
