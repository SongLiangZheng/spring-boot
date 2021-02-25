package com.slz.validate.dto;

import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Data
public class Tender {
    @NotNull(message = "利率不能为空")
    @DecimalMin(value = "0.0001",message = "利率必须大于或等于0.0001")
    private BigDecimal rate;

    @DecimalMin(value = "0.0001",message = "价格必须大于或等于0.0001")
    private BigDecimal price;
}
