package com.example.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
public class MybatisApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisApplication.class, args);
	}
}
