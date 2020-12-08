package com.hs.slz.nacos.server;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

@Configuration
@NacosPropertySource(dataId = "nacos-provider-config2", autoRefreshed = true)
public class NacosConfiguration {
}
