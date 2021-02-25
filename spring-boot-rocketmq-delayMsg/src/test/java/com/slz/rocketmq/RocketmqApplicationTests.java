package com.slz.rocketmq;

import cn.hutool.core.date.DateField;
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
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStarter.class)
public class RocketmqApplicationTests {
    @Resource
    private RocketMQService rocketMQService;

    @Test
    public void send() {
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
