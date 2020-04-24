package com.example.customercondition.service;

import com.example.customercondition.CustomConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author: zhengsl26931
 * @create: 2020-04-24
 */
@Service
@CustomConditionalOnProperty(name = "conditionKey",havingValue = {"2.0","4.0"})
public class TestServiceImpl2 implements TestService{

    @Override
    public String test() {
        return "test 2.0 or 4.0";
    }
}
