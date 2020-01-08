package com.example.mybatis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/session")
public class SessionController {
    @GetMapping("/test")
    public String test(HttpSession session){
        session.setAttribute("name","zhangsan");
        String name=session.getAttribute("name").toString();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        System.out.println("maxInactiveInterval->"+maxInactiveInterval);
        System.out.println(name);
        return "hello world";
    }
}
