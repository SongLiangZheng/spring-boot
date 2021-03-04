package com.slz.rocketmq.delayMsg.rocketMq;

import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.slz.rocketmq.delayMsg.dto.InterceptMessageDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptMsgDTO;
import com.slz.rocketmq.delayMsg.dto.InterceptNoticeEnum;
import com.slz.rocketmq.delayMsg.dto.RocketMqDelayLevelEnum;
import com.slz.rocketmq.delayMsg.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RocketMQMessageListener(consumerGroup = "TestConsumerGroup",
        topic = BondInterceptListener.INTERCEPT_TOPIC,selectorExpression = "AAA")
@Slf4j
public class BondInterceptListener implements RocketMQListener<InterceptMessageDTO> {
    public static final String INTERCEPT_TOPIC = "bondIntercept";
    public static final Long MS_OF_MINUTE = 1000 * 60L;
    @Resource
    private RocketMQService rocketMQService;
    @Resource
    private UserService userService;

    @Override
    public void onMessage(InterceptMessageDTO dto) {
        log.info("BondInterceptListener-message：{}", JSONUtil.toJsonStr(dto));
        if (checkIfResend(dto)) {
            return;
        }
        process(dto);
        userService.asyncHello("张三");
    }

    private boolean checkIfResend(InterceptMessageDTO dto) {
        Long delayTime = dto.getDelayTime();
        if (delayTime != null && delayTime > 0) {
            RocketMqDelayLevelEnum nearestDelayLevel = RocketMqDelayLevelEnum.getNearestDelayLevel(delayTime);
            long leftDelayTime = delayTime - nearestDelayLevel.getDelayTime();
            if (leftDelayTime > 0) {
                dto.setDelayTime(leftDelayTime);
                log.info("BondInterceptListener-resend");
                rocketMQService.sendDelayMsg(INTERCEPT_TOPIC, dto);
                return true;
            }
        }
        return false;
    }


    private void process(InterceptMessageDTO messageDto) {
        InterceptMsgDTO interceptMsgDto = messageDto.getInterceptMsgDTO();
        InterceptNoticeEnum msgTypeEnum = messageDto.getInterceptNoticeEnum();
        if (InterceptNoticeEnum.NOTICE_NOW.equals(msgTypeEnum)) {
            log.info("BondInterceptListener-立即截标：{} 时间:{}",messageDto.getMsgId(),DateTime.now().toString());
        }else{
            doSendInterceptMqMsg(interceptMsgDto, msgTypeEnum, messageDto.getMsgId());
        }
    }

    public void doSendInterceptMqMsg(InterceptMsgDTO interceptMsg, @Nullable InterceptNoticeEnum previousMsgType, String msgId) {
        Date interceptDate = interceptMsg.getInterceptDate();
        InterceptNoticeEnum nextMsgType = getNextMsgType(interceptDate,previousMsgType);
        long delay = interceptDate.getTime() - System.currentTimeMillis() - nextMsgType.getMinute() * MS_OF_MINUTE;
        InterceptMessageDTO interceptMessageDTO = new InterceptMessageDTO(nextMsgType, interceptMsg,delay/ 1000L);
        rocketMQService.sendDelayMsg(INTERCEPT_TOPIC,interceptMessageDTO);
    }

    private InterceptNoticeEnum getNextMsgType(Date interceptDate, @Nullable InterceptNoticeEnum previousMsgType) {
        if (previousMsgType != null && previousMsgType != InterceptNoticeEnum.NOTICE_NOW) {
            return InterceptNoticeEnum.getMsgTypeEnum(previousMsgType.getType() - 1);
        }
        Comparator<InterceptNoticeEnum> comparator = Comparator.comparing(InterceptNoticeEnum::getMinute);
        InterceptNoticeEnum nextMsgType = InterceptNoticeEnum.NOTICE_NOW;
        List<InterceptNoticeEnum> list = Arrays.stream(InterceptNoticeEnum.values())
                .filter(e -> e.getMinute() > 0)
                .sorted(comparator.reversed())
                .collect(Collectors.toList());
        for (InterceptNoticeEnum type : list) {
            Date later = DateUtils.addMinutes(new Date(), type.getMinute());
            if (later.before(interceptDate)) {
                nextMsgType = type;
                break;
            }
        }
        return nextMsgType;
    }
}
