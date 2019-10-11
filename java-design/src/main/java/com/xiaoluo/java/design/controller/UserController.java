package com.xiaoluo.java.design.controller;

import com.xiaoluo.java.design.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classname: UserController
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/9/29 14:17
 */
@RestController
public class UserController {

    @GetMapping
    public void addUser(){
        User user = null;
    }

}
