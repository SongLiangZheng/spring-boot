package com.test.slz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MethodTest {

    @Autowired
    private UserService userService;

    public String One(boolean flag){
        System.err.println("coming.........");
        String d = Two(flag);
        System.err.println("result data is "+d);
        return d;
    }

    public String Two(boolean flag){
        System.err.println("coming.........two");
        if(flag){
            throw new IllegalAccessError();
        }
        return "two";
    }


}
