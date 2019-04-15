package com.app.timetable.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * judithchen
 * 2019/3/27
 * Description：
 **/
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator generator = new AutoGenerator();

        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("D:\\documents");
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false); //ActiveRecode特性
        globalConfig.setEnableCache(false); //XML 二级缓存
        globalConfig.setBaseColumnList(false);
        globalConfig.setBaseResultMap(true);
        globalConfig.setAuthor("judithchen");

        generator.setGlobalConfig(globalConfig);

        //数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("Aliyun963");
        dataSourceConfig.setUrl("jdbc:mysql://47.107.239.122:3306/timetable?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
        generator.setDataSource(dataSourceConfig);

        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); //表名生成策略
        generator.setStrategy(strategyConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.app");
        packageConfig.setModuleName("timetable");
        generator.setPackageInfo(packageConfig);

        //执行生成
        generator.execute();
    }
}
