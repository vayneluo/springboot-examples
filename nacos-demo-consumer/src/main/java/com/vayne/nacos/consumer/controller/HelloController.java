package com.vayne.nacos.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vayne.nacos.provider.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: HelloController
 * @author: Vayne.Luo
 * @date 2020/3/6 11:36
 */
@RestController
public class HelloController {

    @Reference
    HelloService helloService;

    @GetMapping(value = "//hello")
    public String index(@RequestParam("name") String name){
        return helloService.hello(name);
    }

    @GetMapping(value = "/testMapList")
    public List<Map<String, String>> testMapList(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("hello", "nacos-dubbo");
        return helloService.testMapList(map);
    }

}
