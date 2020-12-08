package com.hs.slz.nacos.server;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@NacosPropertySource(dataId = "nacos-provider-config", autoRefreshed = true)
public class NacosProviderStarter {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderStarter.class, args);
    }
//总结：
// 1.可以连接多个PropertySource，以Starter的优先级为最高,其余Conf其次
// 2.本地优先级大于远程
// 3.@NacosValue不设置默认值，且无匹配项时，将启动报错

}