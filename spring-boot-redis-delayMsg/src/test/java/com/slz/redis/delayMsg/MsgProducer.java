package com.slz.redis.delayMsg;

import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

public class MsgProducer {
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        DateTime now = DateTime.now();
        DateTime threeSecondsLater = now.plusSeconds(3);
        DateTime tenSecondsLater = now.plusSeconds(10);
        zSetOperations.add(RedisConfig.DELAY_MESSAGE_SET,"想3秒后被消费",threeSecondsLater.getMillis());
        zSetOperations.add("delayMessagezSet","想10秒后被消费",tenSecondsLater.getMillis());
    }
}
