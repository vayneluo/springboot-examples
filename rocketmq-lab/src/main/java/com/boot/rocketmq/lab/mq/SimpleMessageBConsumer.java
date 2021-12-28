package com.boot.rocketmq.lab.mq;

import com.boot.rocketmq.lab.bean.SimpleMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @classname: SimpleMessageAConsumer
 * @description: B消费者
 * @author: Vayne.Luo
 * @date 2020/8/28 17:03
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = SimpleMessage.TOPIC,consumerGroup = "B_SIMPLE_CONSUMER_GROUP")
public class SimpleMessageBConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        log.info("B [onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), messageExt);
    }
}
