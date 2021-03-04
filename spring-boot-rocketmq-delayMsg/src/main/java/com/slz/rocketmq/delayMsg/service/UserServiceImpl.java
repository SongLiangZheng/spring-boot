package com.slz.rocketmq.delayMsg.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Override
    @Async
    public void asyncHello(String name){
        log.info("hello {}",name);
    }
}
