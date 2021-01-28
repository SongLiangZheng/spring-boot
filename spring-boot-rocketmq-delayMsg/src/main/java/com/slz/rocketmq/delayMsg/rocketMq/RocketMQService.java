package com.slz.rocketmq.delayMsg.rocketMq;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.slz.rocketmq.delayMsg.dto.Delayable;
import com.slz.rocketmq.delayMsg.dto.RocketMqDelayLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
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
        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(delayable).build(), 3000, nearestDelayLevel.getLevel());
        log.info("RocketMQService-发送延时消息：{} topic：{}, delayLevel:{}", JSON.toJSONString(delayable), topic, nearestDelayLevel);
    }

    public void sendMsg(String topic, Object obj) {
        rocketMQTemplate.syncSend(topic, obj);
        log.info("RocketMQService-发送消息：{} topic：{}", JSON.toJSONString(obj), topic);
    }
}
