package com.example.mybatis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MapperAspect {

    public static final String POINT="execution(* com.example.mybatis.dao.*.*(..))";

    @Pointcut(POINT)
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before() {
        System.out.println("============Mapper==========");
    }

    @After("pointCut()")
    public void after() {
        System.out.println("============Mapper==========");
    }
}
