package com.slz.rocketmq.delayMsg.dto;

import cn.hutool.core.lang.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class InterceptMessageDTO implements Serializable, Delayable {
    private String msgId;
    private InterceptNoticeEnum interceptNoticeEnum;
    private InterceptMsgDTO interceptMsgDTO;
    private Long delayTime;

    public InterceptMessageDTO(InterceptNoticeEnum interceptNoticeEnum, InterceptMsgDTO interceptMsgDTO, Long delayTime) {
        this.msgId= UUID.fastUUID().toString(true);
        this.interceptNoticeEnum = interceptNoticeEnum;
        this.interceptMsgDTO = interceptMsgDTO;
        this.delayTime = delayTime;
    }

    public InterceptMessageDTO(){
        this.msgId= UUID.fastUUID().toString(true);
    }
}
