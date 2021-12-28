package com.boot.rocketmq.lab.mq;

import com.boot.rocketmq.lab.bean.SimpleMessage;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @classname: SyncMessageProducer
 * @description: 同步消息生产者
 * @author: Vayne.Luo
 * @date 2020/8/28 16:49
 */
@Component
public class SimpleMessageProducer {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    public SendResult syncSend(Integer messageId){
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setMessageId(messageId);
        // 同步发送
        return rocketMQTemplate.syncSend(SimpleMessage.TOPIC,simpleMessage);
    }

    public void asyncSend(Integer messageId, SendCallback callback){
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setMessageId(messageId);
        // 异步发送
        rocketMQTemplate.asyncSend(SimpleMessage.TOPIC,simpleMessage,callback);
    }

    public void oneWaySend(Integer messageId){
        SimpleMessage simpleMessage = new SimpleMessage();
        simpleMessage.setMessageId(messageId);
        // oneway发送
        rocketMQTemplate.sendOneWay(SimpleMessage.TOPIC,simpleMessage);
    }
}
