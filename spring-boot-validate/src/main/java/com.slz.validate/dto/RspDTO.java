package com.slz.validate.dto;

import com.slz.validate.common.ResponseCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RspDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public RspDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RspDTO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> RspDTO<T> success(T data) {
        RspDTO<T> tRspDTO = new RspDTO<T>();
        tRspDTO.setResponseCode(ResponseCode.SUCCESS);
        tRspDTO.setData(data);
        return tRspDTO;
    }

    protected void setResponseCode(ResponseCode code) {
        this.code = code.val();
        this.message = code.msg();
    }
}
