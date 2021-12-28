package com.xiaoluo.boot.integrate.juc;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @classname: Message
 * @description: java类作用描述
 * @author: Vayne.Luo
 * @date 2021/6/11 16:20
 */
@Data
@EqualsAndHashCode
public class Message {

    private String msg;

    public Message(String msg) {
        this.msg = msg;
    }

   /* public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }*/
}
