package com.slz.rocketmq.delayMsg.dto;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lingbiaokong
 * @description
 * @date 2020/9/14
 */
public enum InterceptNoticeEnum {
    NOTICE_NOW(0, "立即通知", 0),
    FIVE_MINUTE_LATER_NOTICE(1, "五分钟后截标通知", 5),
    TEN_MINUTE_LATER_NOTICE(2, "十分钟后截标通知", 10),
    FIFTEEN_MINUTE_LATER_NOTICE(3, "十五分钟后截标通知", 15),
    THIRTY_MINUTE_LATER_NOTICE(4, "三十分钟后截标通知", 30),
    ;

    private Integer type;
    private String desc;
    private Integer minute;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static InterceptNoticeEnum getMsgTypeEnum(Integer type) {
        List<InterceptNoticeEnum> msgTypeEnums = Arrays.stream(values()).filter(msgType ->
            msgType.getType().equals(type)).collect(Collectors.toList());
        Assert.notEmpty(msgTypeEnums, String.format("msgTypeEnum with type: %d not exit", type));
        return msgTypeEnums.get(0);
    }

    public Integer getMinute() {
        return minute;
    }

    InterceptNoticeEnum(Integer type, String desc, Integer minute) {
        this.type = type;
        this.desc = desc;
        this.minute = minute;
    }
}
