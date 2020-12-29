package com.test.slz.rabbitmq;

import com.test.slz.rabbitmq.config.RabbitMqConfig;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageServiceImpl {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendDelayMsg(String msg, Integer delay) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:" + sdf.format(new Date()));
        rabbitTemplate.convertAndSend(RabbitMqConfig.DELAY_EXCHANGE, RabbitMqConfig.DELAY_QUEUE, msg, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", delay);
                return message;
            }
        });
    }
}
