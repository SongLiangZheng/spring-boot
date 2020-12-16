package com.hs.slz.common.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hs.slz.common.serializer.MoneySerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bond {
    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//前端传参时，必须符合该格式
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")//后台返回对象时的序列化格式
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd")//后台返回对象时的序列化格式
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//前端传参时，必须符合该格式 -若不做限制默认必须符合yyyy-MM-dd格式
    private Date modDate;

    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal money;
}
