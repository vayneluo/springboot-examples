package org.vayne.websocket.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @classname: ChatForm
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/3/31 11:23
 */
@Data
public class ChatForm implements Serializable {

    private String sid;

    private String message;
}
