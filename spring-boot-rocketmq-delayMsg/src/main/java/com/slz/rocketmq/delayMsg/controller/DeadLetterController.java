package com.slz.rocketmq.delayMsg.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.slz.rocketmq.delayMsg.dto.InterceptMessageDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptMsgDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptNoticeEnum;
import com.slz.rocketmq.delayMsg.rocketMq.BondInterceptListener;
import com.slz.rocketmq.delayMsg.rocketMq.RocketMQService;
import com.slz.rocketmq.delayMsg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class DeadLetterController {

    @Resource
    private UserService userService;

    @GetMapping("delayMsg")
    public void delayMsg(){
        userService.test();
    }



}
