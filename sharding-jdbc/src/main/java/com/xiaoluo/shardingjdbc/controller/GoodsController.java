package com.xiaoluo.shardingjdbc.controller;

import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import com.xiaoluo.shardingjdbc.entity.Goods;
import com.xiaoluo.shardingjdbc.repository.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 商品Controller
 * @author: Vayne.Luo
 * @date: 2019/9/27 14:38
 */
@RestController
public class GoodsController {

    @Autowired
    private KeyGenerator keyGenerator;

    @Autowired
    private GoodsRepository goodsRepository;

    @GetMapping("save")
    public String save(){
        for(int i= 1 ; i <= 40 ; i ++){
            Goods goods = new Goods();
            goods.setGoodsId((long) i);
            goods.setGoodsName( "shangpin" + i);
            goods.setGoodsType((long) (i+1));
            goodsRepository.save(goods);
        }
        return "success";
    }

    @GetMapping("select")
    public String select(){
        return goodsRepository.findAll().toString();
    }

    @GetMapping("delete")
    public void delete(){
         goodsRepository.deleteAll();
    }

    @GetMapping("query1")
    public Object query1(){
        return goodsRepository.findAllByGoodsIdBetween(10L, 20L);
    }

    @GetMapping("query2")
    public Object query2(){
        List<Long> goodsIds = new ArrayList<>();
        goodsIds.add(10L);
        goodsIds.add(15L);
        goodsIds.add(20L);
        goodsIds.add(25L);
        goodsIds.add(30L);
        return goodsRepository.findAllByGoodsIdIn(goodsIds);
    }
}