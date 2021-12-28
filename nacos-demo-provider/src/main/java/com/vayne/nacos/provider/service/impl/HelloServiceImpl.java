package com.vayne.nacos.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.vayne.nacos.provider.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: HelloServiceImpl
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/3/6 11:21
 */
@Service
@Slf4j
public class HelloServiceImpl implements HelloService {

    @Value("${spring.profiles.active}")
    private String env;

    @Override
    public String hello(String name) {
        log.info("打印姓名：{}",name);
        return "hello, " + name;
    }

    @Override
    public List<Map<String, String>> testMapList(Map<String, String> map) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        list.add(map);
        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("env", env);
        map2.put("testKey", "testValue");
        list.add(map2);
        return list;
    }

}
