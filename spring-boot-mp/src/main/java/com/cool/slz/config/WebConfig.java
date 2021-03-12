package com.cool.slz.config;

import com.cool.slz.config.convert.MvcParamToEnumConvertFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: web配置类
 * @Author: echo
 * @Date: 2020/11/14 15:48
 * @Version: 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Autowired
//    LoginInterceptor loginInterceptor;
//    @Autowired
//    PrivilegeInterceptor privilegeInterceptor;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 前端入参到枚举类型映射
        registry.addConverterFactory(new MvcParamToEnumConvertFactory());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //自定义拦截器，添加拦截路径和排除拦截路径
//       registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/appid/login", "/api/v1/**", "/error", "/_admin_index.html", "/bond/header/init/**");
//
//        registry.addInterceptor(privilegeInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/appid/login", "/api/v1/**", "/error", "/_admin_index.html", "/bond/header/init/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
