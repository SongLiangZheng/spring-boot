package com.cool.slz;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: MP代码自动生成器
 * @Author: echo
 * @Date: 2020/10/21 17:29
 * @Version: 1.0
 */
@Slf4j
public class MysqlGenerator extends AutoGenerator {

    private MysqlGenerator() {
        super();
        this.init();
    }

    private static void autoGenerate() {
        MysqlGenerator mysqlGenerator = new MysqlGenerator();
        mysqlGenerator.execute();
    }

    public static void main(String[] args) throws IOException {
        // 注意： TODO的地方需要修改
        //重复执行将覆盖原文件
        autoGenerate();
    }

    private void init() {
        AutoGenerator mpg = this;
        String projectPath = "D:/workspace/spring-boot";
        // 全局配置
        initGlobalConfig(mpg, projectPath);

        // 数据源配置
        initDataSourceConfig(mpg);

        // 包配置
        PackageConfig pc = initPackageConfig(mpg);

        // 模板引擎配置
        // TODO: 最后一个参数默认false, 会生成service/controller等文件, 如果不是首次生成对应模块的文件，建议修改成true,防止业务代码被覆盖
        initTemplateConfig(mpg, projectPath, pc, false);

        // 策略配置
        initStrategyConfig(mpg);
    }

    private void initStrategyConfig(AutoGenerator mpg) {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        // TODO：设置表名
        strategy.setInclude(
                "user"
        );
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
//        strategy.setTablePrefix("t_");
//        strategy.setFieldPrefix("F");
        strategy.setEntitySerialVersionUID(false);
        // 有坑，setSuperEntityClass一定要在setSuperEntityColumns前面，否则子类还是对生成父类的字段
        /*strategy.setSuperEntityClass("BaseBondHeaderEntity");
        strategy.setSuperEntityColumns(
                "Fcompany_id",
                "Fgroup_id",
                "Fuser_id",
                "Fheader_name",
                "Fdata_status",
                "Fcreate_time",
                "Fmodify_time",
                "Fheader_type",
                "Ffunction_type"
        );*/
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
    }

    private void initTemplateConfig(AutoGenerator mpg, String projectPath, PackageConfig pc, boolean onlyGenerateEntity) {
        // 自定义配置，模板引擎需要用到外部属性时，可以在这里注入
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/bond-sale-bids/src/main/resources/mappers/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
//        templateConfig.set
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        if (onlyGenerateEntity) {
            templateConfig.disable(TemplateType.SERVICE, TemplateType.CONTROLLER, TemplateType.MAPPER);
        }

        mpg.setTemplate(templateConfig);
    }

    @NotNull
    private PackageConfig initPackageConfig(AutoGenerator mpg) {
        PackageConfig pc = new PackageConfig();
        // TODO: 模块名
        pc.setModuleName("client");
        pc.setParent("com.cool.slz");
        mpg.setPackageInfo(pc);
        return pc;
    }

    private void initDataSourceConfig(AutoGenerator mpg) {
        ClassPathResource resource = new ClassPathResource("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(resource.getStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Console.log("Properties: {}", properties);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(properties.getProperty("spring.datasource.url"));
        dsc.setDriverName(properties.getProperty("spring.datasource.driver-class-name"));
        dsc.setUsername(properties.getProperty("spring.datasource.username"));
        dsc.setPassword(properties.getProperty("spring.datasource.password"));
        mpg.setDataSource(dsc);
    }

    private void initGlobalConfig(AutoGenerator mpg, String projectPath) {
        GlobalConfig gc = new GlobalConfig();
        // Mapper.xml的基础配置
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 代码文件输出路径
        gc.setOutputDir(projectPath + "/spring-boot-mp/src/main/java");
        // TODO: 作者
        gc.setAuthor("zhengsongliang");
        // 开启Swagger
        gc.setSwagger2(true);
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setEntityName("%s");
        mpg.setGlobalConfig(gc);
    }

    public ConfigBuilder getConfigBuilder() {
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(this.getPackageInfo(), this.getDataSource(),
                    this.getStrategy(), this.getTemplate(), this.getGlobalConfig());
            if (null != injectionConfig) {
                injectionConfig.setConfig(config);
            }
        }

        return this.pretreatmentConfigBuilder(config);
    }

}
