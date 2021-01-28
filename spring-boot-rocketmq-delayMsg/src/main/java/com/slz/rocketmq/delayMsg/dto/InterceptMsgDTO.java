package com.slz.rocketmq.delayMsg.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 截标信息
 * @Author chengsj
 * @Date 2020/12/1 16:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterceptMsgDTO {

    /**
     * 机构id
     */
    private String orgId;

    /**
     * 债券id
     */
    private Long bondId;

    /**
     * 是否截标：1-已截标  0-未截标
     */
    private Integer isIntercept;

    /**
     * 截标日期
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date interceptDate;

}
