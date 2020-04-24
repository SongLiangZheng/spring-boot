package com.example.customercondition.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: zhengsl26931
 * @create: 2020-04-24
 */
@Service
@ConditionalOnProperty(name = "conditionKey",havingValue = "2.0")
public class HelloServiceImpl2 implements HelloService{

    @Override
    public String hello() {
        return "hello 2.0";
    }
}
