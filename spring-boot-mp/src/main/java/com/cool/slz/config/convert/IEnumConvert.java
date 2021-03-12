package com.cool.slz.config.convert;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

/**
 * @Description: 枚举参数绑定转换接口
 * <p>
 * 注意：需要mvc入参到枚举类型的转换必须实现该接口！！！
 * 出参转换通过@JsonValue指定
 * </p>
 * @Author: echo
 * @Date: 2020/11/14 15:38
 * @Version: 1.0
 */
@JsonDeserialize(using = EnumJacksonDeserializer.class)
public interface IEnumConvert<T extends Serializable> {

    /**
     * 前端传参映射存储值
     *
     * @return code
     */
    @JsonValue
    T getCode();

    /**
     * 通过字面量获取枚举
     *
     * @param enumCode 字面量
     * @param clazz    枚举类
     * @return 枚举
     */
    static <E extends Enum<E>> IEnumConvert literalValueOf(String enumCode, Class<E> clazz) {
        return (IEnumConvert) Enum.valueOf(clazz, enumCode);
    }

    /**
     * 通过jsonValue注解指定的列转换
     *
     * @param jsonValueCode code
     * @param clazz         枚举类
     * @return 枚举
     */
    static <E extends IEnumConvert, T> IEnumConvert valueOf(T jsonValueCode, Class<E> clazz) {
        E[] enumConstants = clazz.getEnumConstants();
        for (E enumConstant : enumConstants) {
            if (enumConstant.getCode().equals(jsonValueCode)) {
                return enumConstant;
            }
        }

        return null;
    }

}
