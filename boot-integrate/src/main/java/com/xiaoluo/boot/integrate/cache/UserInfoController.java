package com.xiaoluo.boot.integrate.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class UserInfoController {

    @Autowired
    CacheManager cacheManager;
    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/userInfo/{id}")
    public Object getUserInfo(@PathVariable Integer id) {
        UserInfo userInfo = userInfoService.getByName(id);
        if (userInfo == null) {
            return "没有该用户";
        }
        return userInfo;
    }
    @GetMapping("/userInfo/list-all")
    public List<UserInfo> listAllUser() {
        return userInfoService.listAllUser();
    }

    //,key = "#type"
    @Cacheable(cacheNames = "hot-supplier",key = "#type")
    @GetMapping("/userInfo/list-all/{type}/{temp}")
    public List<UserInfo> listAllUserByType(@PathVariable Integer type,@PathVariable Integer temp) {
        log.info("无缓存进入");
        UserInfo info1 = new UserInfo();
        info1.setName("xxx");
        UserInfo info2 = new UserInfo();
        info2.setName("yyy");
        return Collections.singletonList(type == 10 ? info1 : info2);
    }

    @Cacheable("dynamic")
    @GetMapping("/userInfo/list")
    public String list() {
        log.info("无缓存进入");
        return "haizol dynamic";
    }


    @PostMapping("/userInfo")
    public Object createUserInfo(@RequestBody UserInfo userInfo) {
        userInfoService.addUserInfo(userInfo);
        return "SUCCESS";
    }

    @PutMapping("/userInfo")
    public Object updateUserInfo(@RequestBody UserInfo userInfo) {
        UserInfo newUserInfo = userInfoService.updateUserInfo(userInfo);
        if (newUserInfo == null){
            return "不存在该用户";
        }
        return newUserInfo;
    }

    @DeleteMapping("/userInfo/{id}")
    public Object deleteUserInfo(@PathVariable Integer id) {
        userInfoService.deleteById(id);
        return "SUCCESS";
    }

}