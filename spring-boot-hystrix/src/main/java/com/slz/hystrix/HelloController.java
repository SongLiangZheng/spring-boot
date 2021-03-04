package com.slz.hystrix;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String hi(String name) {
        return helloService.sayHello( name );
    }
}
