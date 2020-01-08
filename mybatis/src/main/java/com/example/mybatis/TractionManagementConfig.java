package com.example.mybatis;


import java.io.IOException;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * AOP事务配置
 */
@Configuration
@EnableTransactionManagement
public class TractionManagementConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.example.mybatis.service.impl.*.*(..))";
//    private static final String AOP_POINTCUT_EXPRESSION = "execution(* com.example.mybatis.dao.*.*(..))";

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute requiredTx = new DefaultTransactionAttribute();
        requiredTx.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requiredTx.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);

        DefaultTransactionAttribute readOnly = new DefaultTransactionAttribute();
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        readOnly.setReadOnly(true);

    NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("create*", requiredTx);
        source.addTransactionalMethod("insert*", requiredTx);
        source.addTransactionalMethod("update*", requiredTx);
        source.addTransactionalMethod("delete*", requiredTx);
        source.addTransactionalMethod("operate*", requiredTx);
        source.addTransactionalMethod("save*", requiredTx);
        source.addTransactionalMethod("exec*", requiredTx);
        source.addTransactionalMethod("add*", requiredTx);
        source.addTransactionalMethod("get*", requiredTx);
        source.addTransactionalMethod("modify*",requiredTx);
        source.addTransactionalMethod("remove*", requiredTx);
        source.addTransactionalMethod("merge*", requiredTx);
        source.addTransactionalMethod("commit*", requiredTx);
        source.addTransactionalMethod("*",readOnly);
        return new TransactionInterceptor(dataSourceTransactionManager, source);
}

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}

