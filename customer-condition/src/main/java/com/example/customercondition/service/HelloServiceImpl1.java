package com.example.customercondition.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: zhengsl26931
 * @create: 2020-04-24
 */
@Service
@ConditionalOnProperty(name = "conditionKey",havingValue = "1.0",matchIfMissing = true)
public class HelloServiceImpl1 implements HelloService{

    @Override
    public String hello() {
        return "hello 1.0";
    }
}
