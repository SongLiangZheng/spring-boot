package com.example.customercondition;

import com.example.customercondition.service.HelloService;
import com.example.customercondition.service.TestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomerConditionApplicationTests {
    @Autowired
    private HelloService helloService;
    @Autowired
    private TestService testService;
    @Test
    void contextLoads() {
        String hello = helloService.hello();
        System.out.println(hello);

        String test = testService.test();
        System.out.println(test);
    }
}
