package com.example.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;

/**
 * @author zhengsl26931
 */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Autowired
    private DataSource dataSource;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ScriptRunner scriptRunner = new ScriptRunner(dataSource.getConnection());
        scriptRunner.setStopOnError(true);
        String path = "mysql";
        Resource[] resources = new PathMatchingResourcePatternResolver()
                .getResources("classpath:" + path + "/*.sql");
        scriptRunner.runScript(Resources.getResourceAsReader(path + "/" + resources[0].getFilename()));
    }
}
