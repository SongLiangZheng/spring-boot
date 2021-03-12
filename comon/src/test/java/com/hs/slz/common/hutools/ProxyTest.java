package com.hs.slz.common.hutools;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.TimeIntervalAspect;
import cn.hutool.bloomfilter.BitMapBloomFilter;
import com.hs.slz.common.dto.Bond;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @description:
 * @author: zsl
 * @time: 2021/3/10 10:22
 */
public class ProxyTest {
    @Test
    public void testProxy() {
        Bond proxy = ProxyUtil.proxy(new Bond(), TimeIntervalAspect.class);
        proxy.setName("zhangsan");
        proxy.setMoney(BigDecimal.ONE);
    }


    @Test
    public void testBloomFilter() {
        // 初始化
        BitMapBloomFilter filter = new BitMapBloomFilter(10);
        filter.add("123");
        filter.add("abc");
        filter.add("ddd");

        // 查找
        System.out.println(filter.contains("abc"));
    }
}
