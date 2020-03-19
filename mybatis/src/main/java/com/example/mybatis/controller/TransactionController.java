package com.example.mybatis.controller;

import com.example.mybatis.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private IUserService userService;

    @GetMapping("require/{name}")
    public void require(@PathVariable String name) {
        userService.require(name);
    }

    @GetMapping("notRequire/{name}")
    public void notRequire(@PathVariable String name) {
        userService.notRequire(name);
    }

    @GetMapping("requireCallNotRequire/{name}")
    public void requireCallNotRequire(@PathVariable String name) {
        userService.requireCallNotRequire(name);
    }


    @GetMapping("createRequire/{name}")
    public void createRequire(@PathVariable String name) {
        userService.createRequire(name);
    }

    @GetMapping("createNotRequire/{name}")
    public void createNotRequire(@PathVariable String name) {
        userService.createNotRequire(name);
    }

    @GetMapping("createRequireCallNotRequire/{name}")
    public void createRequireCallNotRequire(@PathVariable String name) {
        userService.createRequireCallNotRequire(name);
    }

}
