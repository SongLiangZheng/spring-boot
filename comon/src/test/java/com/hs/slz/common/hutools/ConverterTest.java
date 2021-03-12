package com.hs.slz.common.hutools;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;

import java.util.Date;
import java.util.List;

public class ConverterTest {
    /*
     * 
     * @param args 
     * @return void
     * @author zhengsongliang
     * @date 2021/3/9 16:43
     */
    public static void main(String[] args) {

        Console.log(new long[]{1,2,3,4,5});

        Console.log(Convert.toIntArray(new String[]{ "1", "2", "3", "4" }));
        Console.log(Convert.toStr(new Date()));
        Console.log(Convert.toDate("2021-03-09 16:54"));

        Console.log(Convert.convert(new TypeReference<List<String>>() {}, new String[]{ "a", "你", "好", "", "a" }));
        String s = "我不是乱码";
        //转换后result为乱码
        String result = Convert.convertCharset(s, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        Assert.assertEquals(raw, s);
    }

    public void testSelfConverter(){
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        converterRegistry.putCustom(String.class, CustomConverter.class);
        int a = 454553;
        Assert.assertEquals("Custom: 454553", converterRegistry.convert(String.class, a));
    }

    public static class CustomConverter implements Converter<String> {
        @Override
        public String convert(Object value, String defaultValue) throws IllegalArgumentException {
            return "Custom: " + value.toString();
        }
    }
}
