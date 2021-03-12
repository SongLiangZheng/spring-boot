package com.cool.slz.config.convert;

import cn.hutool.core.convert.Convert;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * @Description: Mvc入参到枚举类的转换工厂类
 * @Author: echo
 * @Date: 2020/11/14 15:48
 * @Version: 1.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class MvcParamToEnumConvertFactory implements ConverterFactory<String, IEnumConvert> {

    @NotNull
    @Override
    public <T extends IEnumConvert> Converter<String, T> getConverter(@NotNull Class<T> targetType) {
        return new StringToEnum(getEnumType(targetType));
    }

    private static class StringToEnum<T extends IEnumConvert> implements Converter<String, T> {

        private T[] values;

        StringToEnum(Class<T> enumType) {
            this.values = enumType.getEnumConstants();
        }

        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                throw new IllegalArgumentException("不合法的枚举值");
            }

            // 匹配对应的枚举值
            for (T value : values) {
                if (Objects.equals(Convert.toStr(value.getCode()), source.trim())) {
                    return value;
                }
            }

            // 匹配不到，说明前端传参有问题
            throw new IllegalArgumentException("枚举传参有问题");
        }
    }

    /**
     * 获取目标类的枚举类型
     *
     * @param targetType 目标类
     * @return 枚举类类型
     */
    private static Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Assert.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
        return enumType;
    }
}
