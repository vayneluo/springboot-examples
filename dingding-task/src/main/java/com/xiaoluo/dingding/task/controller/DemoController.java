package com.xiaoluo.dingding.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @classname: DemoController
 * @description: 测试
 * @author: Vayne.Luo
 * @date 2019/10/31 16:49
 */
@RestController
public class DemoController {

    @GetMapping("/test")
    public void testRest(HttpServletRequest request){
        System.out.println("----------");
    }
}
