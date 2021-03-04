package com.slz.hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @HystrixCommand(fallbackMethod = "helloError",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "5"),
                    @HystrixProperty(name = "maximumSize", value = "5"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public String sayHello(String name) {
        if(!name.contains("normal")){
            try {
                Thread.sleep( 3000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "hello";
    }

    private String helloError(String name) {
        return "error";
    }

}