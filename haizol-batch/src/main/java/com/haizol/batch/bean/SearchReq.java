package com.haizol.batch.bean;

import lombok.Data;

/**
 * @classname: SearchReq
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2020/9/7 16:07
 */
@Data
public class SearchReq {

    /** 每个批次最大公司ID **/
    private Long maxCompId;
}
