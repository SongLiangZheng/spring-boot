package com.hs.slz.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.slz.common.dto.Bond;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class LocalDateTimeTest {
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSerAndDeSer() throws JsonProcessingException {
        Bond bond = new Bond();
        bond.setName("11");
        bond.setCreateTime(new Date());
        bond.setModDate(new Date());
        bond.setMoney(BigDecimal.valueOf(12.333333));
        System.out.println(objectMapper.writeValueAsString(bond));//仅对jackson序列化接口生效
    }
}
