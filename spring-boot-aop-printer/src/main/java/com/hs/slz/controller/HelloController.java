package com.hs.slz.controller;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController("")
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(String name) {
        log();
        return "hello "+name;
    }

    private void log() {
        log.info("开始");
        log.info("结束");
    }

}
