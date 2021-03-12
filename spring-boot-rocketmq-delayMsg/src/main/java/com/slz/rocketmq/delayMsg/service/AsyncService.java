package com.slz.rocketmq.delayMsg.service;

import org.springframework.scheduling.annotation.Async;

public interface AsyncService {
    @Async
    void asyncHello(String name);

    @Async
    void sleep(long time);
}
