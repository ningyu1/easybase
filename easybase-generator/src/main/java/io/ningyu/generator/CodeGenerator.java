package io.ningyu.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 * 代码生成器演示
 * </p>
 */
public class CodeGenerator {

    public static void main(String[] args) {
        //作者
        String author = scanner("请输入作者（默认值ningyu）", "ningyu");
        //表明
        String[] tables = scanner("请输入表明（多个用逗号分隔）", null).split(",");
        //表前缀
        String[] tablePrefix = scanner("请输入表前缀（多个用逗号分隔）", null).split(",");
        //包名
        String basePackage = scanner("请输入包名（默认值io.ningyu）", "io.ningyu");
        //模块名
        String moduleName = scanner("请输入模块名", null);
        //后端代码路径
        String backendOutPutDir = scanner("请输入后端代码路径（默认值./generatorCode）", "./generatorCode");
        //前端代码路径
        String frontendOutPutDir = scanner("请输入前端代码路径（默认值./generatorCode）", "./generatorCode");
        codeGenerate(Generator.builder().author(author).tables(tables).tablePrefix(tablePrefix)
                .basePackage(basePackage).moduleName(moduleName).backendOutPutDir(backendOutPutDir)
                .frontendOutPutDir(frontendOutPutDir).build());
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip, String defaultValue) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        while (scanner.hasNextLine()) {
            String ipt = scanner.nextLine();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            } else if (StringUtils.isNotBlank(defaultValue)) {
                return defaultValue;
            } else {
                System.out.println("输入不正确！");
            }
        }
        throw new MybatisPlusException("输入不正确！");
    }

    /**
     * <p>
     * 代码生成
     * </p>
     */
    public static void codeGenerate(Generator generator) {
        //作者
        String author = generator.getAuthor();
        //表明
        String[] tables = generator.getTables();
        //表前缀
        String[] tablePrefix = generator.getTablePrefix();
        //包名
        String basePackage = generator.getBasePackage();
        //模块名
        String moduleName = generator.getModuleName();
        //后端代码路径
        String backendOutPutDir = generator.getBackendOutPutDir().endsWith("/") ? generator.getBackendOutPutDir().substring(0, generator.getBackendOutPutDir().length() - 1) : generator.getBackendOutPutDir();
        //后端代码路径
        String frontendOutPutDir = generator.getFrontendOutPutDir().endsWith("/") ? generator.getFrontendOutPutDir().substring(0, generator.getFrontendOutPutDir().length() - 1) : generator.getFrontendOutPutDir();

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        mpg.setGlobalConfig(new GlobalConfig().setOutputDir(backendOutPutDir)
                // 作者
                .setAuthor(author)
                //生成后打开文件夹
                .setOpen(false)
                //增加swagger描述
                .setSwagger2(false)
                // 数据库ID自增
                .setIdType(IdType.AUTO)
                // 是否覆盖文件
                .setFileOverride(true)
                //开启 activeRecord 模式
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%sEntity")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController"));

        // 数据源配置
        mpg.setDataSource(new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("root")
                .setUrl("jdbc:mysql://127.0.0.1:3306/jeebase?useUnicode=true&characterEncoding=utf8"));

        // 包配置
        mpg.setPackageInfo(new PackageConfig()
                .setParent(basePackage)// 自定义包路径
                .setModuleName(moduleName)//模块名
                .setController("controller")// 这里是控制器包名，默认 web
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("mapper"));

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {

            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("DtoPackage", mpg.getPackageInfo().getParent() + ".dto");
                this.setMap(map);
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        //controller
        focList.add(new FileOutConfig("/templates/controller.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = mpg.getPackageInfo().getParent() + StringPool.DOT + mpg.getPackageInfo().getController() + StringPool.DOT + tableInfo.getControllerName();
                // 自定义输入文件名称
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //Entity
        focList.add(new FileOutConfig("/templates/entity.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = mpg.getPackageInfo().getParent() + StringPool.DOT + mpg.getPackageInfo().getEntity() + StringPool.DOT + tableInfo.getEntityName();
                // 自定义输入文件名称
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //dto
        focList.add(new FileOutConfig("/templates/dto.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String dtoName = "Query" + tableInfo.getEntityName().replaceAll("Entity", "");
                String path = mpg.getPackageInfo().getParent() + ".dto." + dtoName;
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //mapper.java
        focList.add(new FileOutConfig("/templates/mapper.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = mpg.getPackageInfo().getParent() + StringPool.DOT + mpg.getPackageInfo().getMapper() + StringPool.DOT + tableInfo.getMapperName();
                // 自定义输入文件名称
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //mapper.xml
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                return backendOutPutDir + "/src/main/resources/mappers/" + mpg.getPackageInfo().getModuleName() + StringPool.SLASH
                        + tableInfo.getXmlName() + StringPool.DOT_XML;
            }
        });
        //service.java
        focList.add(new FileOutConfig("/templates/service.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = mpg.getPackageInfo().getParent() + StringPool.DOT + mpg.getPackageInfo().getService() + StringPool.DOT + tableInfo.getServiceName();
                // 自定义输入文件名称
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //serviceImpl.java
        focList.add(new FileOutConfig("/templates/serviceImpl.java.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String path = mpg.getPackageInfo().getParent() + StringPool.DOT + mpg.getPackageInfo().getServiceImpl() + StringPool.DOT + tableInfo.getServiceImplName();
                // 自定义输入文件名称
                path = backendOutPutDir + "/src/main/java/" + path.replaceAll("\\.", StringPool.SLASH) + StringPool.DOT_JAVA;
                return path;
            }
        });
        //ui.vue
        focList.add(new FileOutConfig("/templates/ui.vue.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String name= (new StringBuilder()).append(Character.toLowerCase(tableInfo.getEntityName().charAt(0))).append(tableInfo.getEntityName().substring(1)).toString().replace("Entity", "");
                // 自定义输入文件名St称
                String path = frontendOutPutDir + "/src/views/system/"+ name +"/" + name + "Table.vue";
                return path;
            }
        });
        //ui.js
        focList.add(new FileOutConfig("/templates/ui.js.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                String name= (new StringBuilder()).append(Character.toLowerCase(tableInfo.getEntityName().charAt(0))).append(tableInfo.getEntityName().substring(1)).toString().replace("Entity", "");
                // 自定义输入文件名称
                String path = frontendOutPutDir + "/src/api/system/"+ name + ".js";
                return path;
            }
        });
        //ui.lang.js
        focList.add(new FileOutConfig("/templates/ui.lang.js.ftl") {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                String path = frontendOutPutDir + "/src/lang/zh1.js";
                return path;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        /**
         * 模板配置
         */
        TemplateConfig tc = new TemplateConfig();
        //关闭自动默认生成，使用上面自定义生成
        mpg.setTemplate(tc.setXml(null).setController(null).setEntity(null).setService(null).setServiceImpl(null).setMapper(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(tablePrefix);// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(tables);// 表名
        // strategy.setSuperEntityColumns("id");
        // strategy.setControllerMappingHyphenStyle(true);
        // strategy.setTablePrefix(pc.getModuleName() + "_");
        //Boolean类型字段是否移除is前缀处理
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        strategy.entityTableFieldAnnotationEnable(true);
        strategy.setLogicDeleteFieldName("del_flag");
        List<TableFill> tableFillList = new ArrayList<TableFill>();
        TableFill tableFill1 = new TableFill("create_time", FieldFill.INSERT);
        TableFill tableFill2 = new TableFill("create_user", FieldFill.INSERT);
        TableFill tableFill3 = new TableFill("update_time", FieldFill.UPDATE);
        TableFill tableFill4 = new TableFill("update_user", FieldFill.UPDATE);
        tableFillList.add(tableFill1);
        tableFillList.add(tableFill2);
        tableFillList.add(tableFill3);
        tableFillList.add(tableFill4);
        strategy.setTableFillList(tableFillList);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
