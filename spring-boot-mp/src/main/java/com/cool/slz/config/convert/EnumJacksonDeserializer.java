package com.cool.slz.config.convert;

import cn.hutool.core.convert.Convert;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ResolvableType;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

/**
 * @Description: 重写jackson枚举反序列化, 支持按字面量(ENABLE)转换, jsonValue注解指定属性转换, 去除按ordinal转换
 * @Author: echo
 * @Date: 2020/11/22 16:22
 * @Version: 1.0
 */
public class EnumJacksonDeserializer extends JsonDeserializer<IEnumConvert> {

    @SneakyThrows
    @Override
    @SuppressWarnings("unchecked")
    public IEnumConvert deserialize(JsonParser jp, DeserializationContext ctxt) {
        JsonNode node = jp.getCodec().readTree(jp);
        // 属性名
        String currentName = jp.currentName();
        // 属性对象
        Object currentValue = jp.getCurrentValue();
        // 获取枚举的类类型
        Class enumType = getEnumType(jp, currentName, currentValue);
        IEnumConvert iEnumConvert = null;
        // 字符串类型(字面量/数字)
        if (node.isTextual()) {
            String name = node.asText();
            Integer code = Convert.toInt(name);
            try {
                iEnumConvert = Objects.isNull(code) ?
                        IEnumConvert.literalValueOf(name, enumType) :
                        IEnumConvert.valueOf(code, enumType);
            } catch (Exception e) {
                iEnumConvert = IEnumConvert.valueOf(name, enumType);

            }
        } else if (node.isNumber()) {
            iEnumConvert = IEnumConvert.valueOf(node.asInt(), enumType);
        }

        if (iEnumConvert == null) {
            // 匹配不到，说明前端传参有问题
            throw new IllegalArgumentException("枚举传参有问题");
        }
        return iEnumConvert;
    }

    /**
     * 获取枚举类类型
     *
     * @param jp           JsonParser
     * @param currentName  属性名
     * @param currentValue 属性对象
     * @return 枚举类
     * @throws NoSuchFieldException
     */
    private Class getEnumType(JsonParser jp,
                              String currentName,
                              Object currentValue) throws NoSuchFieldException {
        Class enumType;
        // List<Enum>集合类型需要特殊处理
        if (currentValue instanceof Collection) {
            JsonStreamContext parsingContext = jp.getParsingContext();
            JsonStreamContext parent = parsingContext.getParent();
            // 集合对象
            Object collection = parent.getCurrentValue();
            String fieldName = parent.getCurrentName();
            Field listField = collection.getClass().getDeclaredField(fieldName);
            enumType = ResolvableType.forField(listField).getGeneric(0).resolve();
        } else {
            enumType = BeanUtils.findPropertyType(currentName, currentValue.getClass());
        }
        return enumType;
    }
}
