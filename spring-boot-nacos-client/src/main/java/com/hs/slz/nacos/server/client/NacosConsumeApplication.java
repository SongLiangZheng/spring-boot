package com.hs.slz.nacos.server.client;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
@RestController
public class NacosConsumeApplication {

    @NacosInjected
    private NamingService namingService;

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumeApplication.class, args);
    }

    @GetMapping("/test")
    public void test() throws NacosException {
        // 根据服务名从注册中心获取一个健康的服务实例
        List<Instance> instances = namingService.selectInstances("nacos-provider", true);
        for (Instance instance : instances) {
            // 这里只是为了方便才新建RestTemplate实例
            RestTemplate template = new RestTemplate();
            String url = String.format("http://%s:%d/hello?name=throwable", instance.getIp(), instance.getPort());
            String result = template.getForObject(url, String.class);
            System.out.println(String.format("请求URL:%s,响应结果:%s", url, result));
        }
    }
}