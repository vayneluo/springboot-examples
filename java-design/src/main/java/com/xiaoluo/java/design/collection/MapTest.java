package com.xiaoluo.java.design.collection;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: MapTest
 * @description: Map Test
 * @author: Vayne.Luo
 * @date 2019/10/12 14:40
 */
@Slf4j
public class MapTest {

    public static void main(String[] args) {



        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer n = list.get(i);
            sum = sum + n * n;
            System.out.println("----- 总和为" + sum);
        }



        /*File file = new File("D:\\file.txt");
        if(file.delete()){
            log.info("文件已删除，文件名称：" + file.getName());
        }else {
            log.info("文件不存在，文件名称：" + file.getName());
        }*/
    }
}
