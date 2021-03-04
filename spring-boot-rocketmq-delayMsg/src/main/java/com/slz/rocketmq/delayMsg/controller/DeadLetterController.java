package com.slz.rocketmq.delayMsg.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.slz.rocketmq.delayMsg.dto.InterceptMessageDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptMsgDTO;
import com.slz.rocketmq.delayMsg.rocketMq.BondInterceptListener;
import com.slz.rocketmq.delayMsg.rocketMq.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@Slf4j
public class DeadLetterController {
    @Resource
    private RocketMQService rocketMQService;

    @GetMapping("delayMsg")
    public void delayMsg(){
        Date date = DateUtil.date().offset(DateField.SECOND, 10).toJdkDate();
        for (int i = 0; i < 1; i++) {
            InterceptMessageDTO interceptMessageDTO1 = new InterceptMessageDTO();
            InterceptMsgDTO interceptMsgDTO1=new InterceptMsgDTO();
            interceptMsgDTO1.setBondId((long) i);
            interceptMsgDTO1.setOrgId("机构"+i);
            interceptMsgDTO1.setInterceptDate(date);
            interceptMessageDTO1.setInterceptMsgDTO(interceptMsgDTO1);
            rocketMQService.sendMsg(BondInterceptListener.INTERCEPT_TOPIC, interceptMessageDTO1);
        }
    }


}
