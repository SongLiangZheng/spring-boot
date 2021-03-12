package com.slz.validate.commonException;

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
