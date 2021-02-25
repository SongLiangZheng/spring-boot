package com.slz.rocketmq.delayMsg.rocketMq;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.slz.rocketmq.delayMsg.dto.Delayable;
import com.slz.rocketmq.delayMsg.dto.RocketMqDelayLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class RocketMQService {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void sendDelayMsg(String topic, Delayable delayable) {
        Long delayTime = delayable.getDelayTime();
        RocketMqDelayLevelEnum nearestDelayLevel = RocketMqDelayLevelEnum.getNearestDelayLevel(delayTime);
        Message<Delayable> message = MessageBuilder.withPayload(delayable).build();
        SendResult sendResult = rocketMQTemplate.syncSend(topic, message, 3000, nearestDelayLevel.getLevel());
        log.info("RocketMQService-发送延时消息：{} topic：{}, delayLevel:{},sendStatus:{}",
                JSON.toJSONString(delayable), topic, nearestDelayLevel, sendResult.getSendStatus());
    }

    public void sendMsg(String topic, Object obj) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, obj);
        log.info("RocketMQService-发送消息：{} topic：{},sendStatus:{}", JSON.toJSONString(obj), topic, sendResult.getSendStatus());
    }

    public void sendMsg(String topic, Object obj,String tag) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, obj);
        log.info("RocketMQService-发送消息：{} topic：{},sendStatus:{}", JSON.toJSONString(obj), topic, sendResult.getSendStatus());
    }
}
