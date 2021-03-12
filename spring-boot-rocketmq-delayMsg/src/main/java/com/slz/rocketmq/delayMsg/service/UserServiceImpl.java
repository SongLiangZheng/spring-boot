package com.slz.rocketmq.delayMsg.service;

import com.slz.rocketmq.delayMsg.config.CommConstants;
import com.slz.rocketmq.delayMsg.dto.InterceptMessageDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptMsgDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptNoticeEnum;
import com.slz.rocketmq.delayMsg.rocketMq.RocketMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private RocketMQService rocketMQService;
//    @Resource
//    private AsyncService asyncService;


    @Override
    @Transactional
    public void test() {
        ConcurrentHashMap a;
//        for (int i = 0; i < 12; i++) {
//            asyncService.sleep(1);
//        }

//        Date date = DateUtil.date().offset(DateField.SECOND, 10).toJdkDate();
        InterceptMessageDTO interceptMessageDTO1 = new InterceptMessageDTO();
        InterceptMsgDTO interceptMsgDTO1=new InterceptMsgDTO();
        interceptMsgDTO1.setBondId((long) 1);
        interceptMsgDTO1.setOrgId("机构"+1);
        interceptMsgDTO1.setInterceptDate(new Date());
        interceptMessageDTO1.setInterceptMsgDTO(interceptMsgDTO1);
        interceptMessageDTO1.setInterceptNoticeEnum(InterceptNoticeEnum.NOTICE_NOW);
        rocketMQService.sendMsg(CommConstants.INTERCEPT_TOPIC, interceptMessageDTO1);
        log.info("任务结束");
    }

}
