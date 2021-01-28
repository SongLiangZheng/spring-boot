package com.slz.rocketmq.delayMsg;

import io.github.rhwayfun.springboot.rocketmq.starter.common.AbstractRocketMqConsumer;
import io.github.rhwayfun.springboot.rocketmq.starter.constants.RocketMqContent;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DemoMqConsumer extends AbstractRocketMqConsumer<DemoMqTopic, DemoMqContent> {

    @Override
    public boolean consumeMsg(DemoMqContent content, MessageExt messageExt) {
        System.out.println(new Date() + ", " + content);
        return true;
    }

    @Override
    public Map<String, Set<String>> subscribeTopicTags() {
        Map<String, Set<String>> map = new HashMap<>();
        Set<String> tags = new HashSet<>();
        tags.add("test-tag");
        map.put("test-topic", tags);
        return map;
    }

    @Override
    public String getConsumerGroup() {
        return "test-consumer-group";
    }


}

