package com.cool.slz;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

    public static void main(String[] args) {
        // 注意： TODO的地方需要修改
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
                "t_primary_tender"
        );
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityTableFieldAnnotationEnable(true);
        strategy.setTablePrefix("t_");
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
        pc.setModuleName("bond");
        pc.setParent("com.qtrade.primary.bondsale.bids.refactor.modules");
        mpg.setPackageInfo(pc);
        return pc;
    }

    private void initDataSourceConfig(AutoGenerator mpg) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://212.129.164.73:3306/db_primary_bond_sale?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B8&allowMultiQueries=true");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("NaqcChx3EpCX");
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
        gc.setMapperName("%sEntityMapper");
        gc.setXmlName("%sEntityMapper");
        gc.setEntityName("%sEntity");
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

    public static void test() {
        // 代码生成器
        MysqlGenerator mpg = new MysqlGenerator();

        ConfigBuilder configBuilder = mpg.getConfigBuilder();
        List<TableInfo> tableInfoList = mpg.getAllTableInfoList(configBuilder);
        // tableName -> columnName, TableField
        Map<String, Map<String, TableField>> tablefieldMap = tableInfoList
                .stream()
                .collect(Collectors.toMap(TableInfo::getName,
                        v -> v.getFields().stream()
                                .collect(Collectors.toMap(TableField::getColumnName, value -> value))));

        List<Map<String, TableField>> list = new ArrayList<>(tablefieldMap.values());
        // 四个表合同后的所有字段
        Set<String> allFieldSet = list.stream().flatMap(m -> m.entrySet().stream()).map(Map.Entry::getKey).collect(Collectors.toSet());
        // 四个公共字段
        Set<String> commonFieldSet = list.stream().map(Map::keySet).collect(Collectors.toList())
                .stream()
                .reduce((a, b) -> {
                    Set<String> tempSet = new HashSet<>(a);
                    tempSet.retainAll(b);
                    return tempSet;
                }).orElse(new HashSet<>());
        log.info("四个表公共字段如下, size =[{}]", commonFieldSet.size());
        commonFieldSet.forEach((c) -> {
            TableField tableField = list.get(0).get(c);
            log.info("\t column=[{}], type=[{}], comment=[{}]", c, tableField.getType(), tableField.getComment());
        });
        tablefieldMap.forEach((t, f) -> {
            Set<String> fieldSet = f.keySet();
            fieldSet.removeAll(commonFieldSet);
            log.info("tableName=[{}], 去除四个表的公用字段后的剩余字段如下, size = [{}]", t, fieldSet.size());
            for (String field : fieldSet) {
                log.info("\t column=[{}], type=[{}], comment=[{}]", field, f.get(field).getType(), f.get(field).getComment());
            }


        });
    }
}
