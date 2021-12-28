package com.boot.rocketmq.lab.bean;

import lombok.Data;

/**
 * @classname: SimpleMessage
 * @description: 普通消息
 * @author: Vayne.Luo
 * @date 2020/8/28 16:56
 */
@Data
public class SimpleMessage {

    public static final String TOPIC = "SIMPLE_MESSAGE_TOPIC";

    private Integer messageId;
}
