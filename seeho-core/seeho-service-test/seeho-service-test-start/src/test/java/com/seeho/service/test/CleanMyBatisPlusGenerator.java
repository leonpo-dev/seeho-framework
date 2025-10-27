package com.seeho.service.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leonpo
 * @since 2025-10-25
 */
public class CleanMyBatisPlusGenerator {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");

        String package_module = "seeho.service.test.persistence";


        String entryPath = projectPath +package_module+ "/src/main/java/com/seeho/service/test/persistence/po";
        String mapperPath = projectPath +package_module+ "/src/main/java/com/seeho/service/test/persistence/mapper";
        String mapperXmlPath = projectPath + "/src/main/resources/mapper";
        String servicePath = projectPath +package_module+ "/src/main/java/com/seeho/service/test/persistence/IService";
        String serviceImplPath = projectPath +package_module+ "/src/main/java/com/seeho/service/test/persistence/IService/impl";

        Map<OutputFile,String> pathInfoMap = new HashMap<>();
        pathInfoMap.put(OutputFile.entity, entryPath);
        pathInfoMap.put(OutputFile.mapper, mapperPath);
        pathInfoMap.put(OutputFile.xml, mapperXmlPath);
        pathInfoMap.put(OutputFile.service, servicePath);
        pathInfoMap.put(OutputFile.serviceImpl, serviceImplPath);



        // /Users/guanyf/data/leonpo/seeho-framework/seeho-core/seeho-service-test/src/main/java/com/seeho/service/test/persistence/mapper
        // /Users/guanyf/data/leonpo/seeho-framework/seeho-core/seeho-service-test/seeho-service-test-persistence/src/main/java/com/seeho/service/test/persistence/mapper

        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/three_p_data?useSSL=false&serverTimezone=Asia/Shanghai",
                        "root",
                        "5tgb^YHN.")
                .globalConfig(builder -> builder
                        .author("Leonpo")
                        .disableOpenDir()
                        .commentDate("yyyy-MM-dd")
                        //.outputDir(projectPath + "/src/main/java/com/seeho/service/test/persistence/mapper")
                )
                .packageConfig(builder -> builder
                        .parent("com.seeho")
                        .moduleName("seeho-service-test-persistence") // 可选
                        .pathInfo(pathInfoMap)
                )
                .strategyConfig(builder -> builder
                        //.addInclude("three_p_data")
                        .addTablePrefix("")
                        .entityBuilder().enableLombok()
                        .mapperBuilder()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
                        .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")

                )
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
