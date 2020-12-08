package com.hs.slz.nacos.server.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.naming.NamingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NacosProviderController implements CommandLineRunner {
    @NacosInjected
    private NamingService namingService;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @NacosValue(value = "${address:默认地址：深圳}", autoRefreshed = true)
    private String address;

//    @Value(value = "${age:0}")
    @NacosValue(value = "${age:0}", autoRefreshed = true)
    private String age;

    @NacosValue(value = "${env.name}", autoRefreshed = true)
    private String envName;

    @NacosValue(value = "${remark}", autoRefreshed = true)
    private String remark;


    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name") String name) {
        return String.format("hello %s!", name);
    }

    @GetMapping("/testConfig")
    public Map<String, Object> testConfig() {
        Map<String, Object> map = new HashMap<>();
        map.put("spring.application.name",applicationName);
        map.put("server.port",serverPort);
        map.put("address",address);
        map.put("age",age);
        map.put("envName",envName);
        map.put("remark",remark);
        return map;
    }

    @Override
    public void run(String... args) throws Exception {
        // 通过Naming服务注册实例到注册中心
        namingService.registerInstance(applicationName, "127.0.0.1", serverPort);
    }
}
