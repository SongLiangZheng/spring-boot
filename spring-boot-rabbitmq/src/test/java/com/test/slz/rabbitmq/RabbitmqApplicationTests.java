package com.test.slz.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private MessageServiceImpl messageService;

    @Test
    public void send() {
        messageService.sendDelayMsg("我想在10秒后被消费",10000);
        messageService.sendDelayMsg("我想在3秒后被消费",3000);
    }

}