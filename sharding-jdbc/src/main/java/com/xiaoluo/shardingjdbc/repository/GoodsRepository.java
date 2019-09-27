package com.xiaoluo.shardingjdbc.repository;

import com.xiaoluo.shardingjdbc.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @classname: GoodsRepository
 * @description: 商品接口操作
 * @author: Vayne.Luo
 * @date 2019/9/27 13:58
 */
public interface GoodsRepository extends JpaRepository<Goods,Long> {

    List<Goods> findAllByGoodsIdBetween(Long goodsId1, Long goodsId2);

    List<Goods> findAllByGoodsIdIn(List<Long> goodsIds);
}
