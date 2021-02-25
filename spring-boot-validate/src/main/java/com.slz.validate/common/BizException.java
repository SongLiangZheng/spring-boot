package com.slz.validate.common;

import com.slz.validate.dto.RspDTO;
import lombok.Data;

@Data
public class BizException extends RuntimeException {


    private Integer code;

    public BizException(String msg) {
        super(msg);
    }

    public BizException(RspDTO rspDTO) {
        super(rspDTO.getMessage());
        this.code = rspDTO.getCode();
    }

}
