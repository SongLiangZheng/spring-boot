package com.slz.rocketmq;

import cn.hutool.core.date.DateUtil;
import com.slz.rocketmq.delayMsg.ApplicationStarter;
import com.slz.rocketmq.delayMsg.dto.InterceptMsgDTO;
import com.slz.rocketmq.delayMsg.rocketMq.BondInterceptListener;
import com.slz.rocketmq.delayMsg.rocketMq.RocketMQService;
import com.slz.rocketmq.delayMsg.dto.InterceptMessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class RocketmqApplicationTests {
    @Resource
    private RocketMQService rocketMQService;

    @Test
    public void send() {
        InterceptMessageDTO interceptMessageDTO1 = new InterceptMessageDTO();
        String interceptTimeStr = "2021-01-28 20:10:00";

        InterceptMsgDTO interceptMsgDTO1=new InterceptMsgDTO();
        interceptMsgDTO1.setBondId(2L);
        interceptMsgDTO1.setOrgId("BBB机构");
        interceptMsgDTO1.setInterceptDate(DateUtil.parse(interceptTimeStr));
        interceptMessageDTO1.setInterceptMsgDTO(interceptMsgDTO1);
        rocketMQService.sendMsg(BondInterceptListener.INTERCEPT_TOPIC, interceptMessageDTO1);

        InterceptMessageDTO interceptMessageDTO2 = new InterceptMessageDTO();
        InterceptMsgDTO interceptMsgDTO2=new InterceptMsgDTO();
        interceptMsgDTO2.setBondId(1L);
        interceptMsgDTO2.setOrgId("AAA机构");
        interceptMsgDTO2.setInterceptDate(DateUtil.parse(interceptTimeStr));
        interceptMessageDTO2.setInterceptMsgDTO(interceptMsgDTO2);
        rocketMQService.sendMsg(BondInterceptListener.INTERCEPT_TOPIC, interceptMessageDTO2);
    }

}
