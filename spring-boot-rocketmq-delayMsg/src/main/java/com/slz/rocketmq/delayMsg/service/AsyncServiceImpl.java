package com.slz.rocketmq.delayMsg.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void asyncHello(String name){
        log.info("hello {}",name);
    }


    @Override
    @Async
    public void sleep(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
