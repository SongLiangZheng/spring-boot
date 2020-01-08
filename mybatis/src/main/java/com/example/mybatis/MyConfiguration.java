package com.example.mybatis;

import java.io.IOException;
import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "com.example.mybatis.dao")
public class MyConfiguration {
	@Autowired
	private DataSource dataSource;

    @Bean
	public SqlSessionFactoryBean getSqlSessionFactoryBean() throws IOException {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);
//		factory.setConfigLocation(new ClassPathResource("common-mapper.xml"));
//		Resource[] resources = new PathMatchingResourcePatternResolver()
//				.getResources("classpath:mapper/*.xml");
//		factory.setMapperLocations(resources);
		return factory;
	}

	@Bean
	public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}





}
