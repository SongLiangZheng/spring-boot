package com.slz.rocketmq.delayMsg.dto;

public interface Delayable {
    String getMsgId();
    void setMsgId(String msgId);
    Long getDelayTime();
    void setDelayTime(Long delayTime);
}
