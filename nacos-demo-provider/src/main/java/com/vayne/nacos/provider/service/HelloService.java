package com.vayne.nacos.provider.service;

import java.util.List;
import java.util.Map;

/**
 * @classname: HelloService
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/3/6 11:20
 */
public interface HelloService {

    String hello(String name);

    List<Map<String, String>> testMapList(Map<String, String> map);
}
