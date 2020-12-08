package com.test.slz.controller;

import com.test.slz.entity.Person;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello word";
    }

    @RequestMapping("/hello1/{name}")
    public String hello1(@PathVariable(name = "name") String name) {
        return "hello " + name;
    }

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    public String hello2(String par1, Integer par2) {
        return "hello " + par1 + "," + par2;
    }

    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public String hello3(String par) {
        return "hello " + par;
    }

    @RequestMapping(value = "/hello4")
    public String hello4(@RequestBody String par) {
        return "hello " + par;
    }

    @RequestMapping(value = "/hello5")
    @ResponseBody
    public Person hello5(@RequestBody Person person) {
        return person;
    }
}
