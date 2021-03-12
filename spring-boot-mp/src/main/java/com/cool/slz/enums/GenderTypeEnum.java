package com.cool.slz.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.cool.slz.config.convert.IEnumConvert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderTypeEnum implements IEnumConvert<Integer> {

    MALE(1, "男"),
    FEMALE(2, "女");

    @EnumValue
    private Integer type;

    private String desc;


    @Override
    public Integer getCode() {
        return type;
    }
}
