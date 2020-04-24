package com.example.customercondition.service;

import com.example.customercondition.CustomConditionalOnProperty;
import org.springframework.stereotype.Service;

/**
 * @author: zhengsl26931
 * @create: 2020-04-24
 */
@Service
@CustomConditionalOnProperty(name = "conditionKey",havingValue = {"1.0","3.0"})
public class TestServiceImpl1 implements TestService{

    @Override
    public String test() {
        return "test 1.0 or 3.0";
    }
}
