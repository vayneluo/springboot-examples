package com.xiaoluo.dingding.task.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @classname: BaseVo
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2019/10/30 17:06
 */
@Data
public class BaseVo<T extends RspData> implements Serializable {

    private Integer ret;

    private T data;

    private double qt;
}
