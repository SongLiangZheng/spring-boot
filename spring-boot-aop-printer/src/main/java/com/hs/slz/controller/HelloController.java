package com.hs.slz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("")
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) {
        log.info("开始");
        return "hello "+name;
    }

}
