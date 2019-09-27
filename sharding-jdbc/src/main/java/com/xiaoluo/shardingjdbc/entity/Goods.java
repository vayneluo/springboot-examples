package com.xiaoluo.shardingjdbc.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @classname: Goods
 * @description: 商品
 * @author: Vayne.Luo
 * @date 2019/9/27 13:55
 */
@Data
@Entity
@Table(name = "goods")
public class Goods {

    @Id
    private Long goodsId;

    private String goodsName;

    private Long goodsType;
}
