package com.slz.validate.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderDTO extends Tender{

    private Long id;

    @NotEmpty(message = "订单描述不能为空")
    private String desc;

}
