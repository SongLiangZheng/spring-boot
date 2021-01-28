package com.slz.redis.delayMsg;

import org.joda.time.DateTime;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.Set;

public class DelayMsgConsumer implements Runnable {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void run() {
        ZSetOperations ops = redisTemplate.opsForZSet();
        long millis = DateTime.now().plusSeconds(3).getMillis();
        Set set = ops.rangeByScore(RedisConfig.DELAY_MESSAGE_SET, 0, millis);
        set.forEach(e -> System.out.println(e));
        ops.removeRangeByScore(RedisConfig.DELAY_MESSAGE_SET, 0, millis);


    }
}
