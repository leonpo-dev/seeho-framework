package com.seeho.service.test;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Leonpo
 * @since 2025-10-25
 */
public class CleanMyBatisPlusGenerator {
    public static void main(String[] args) {
        // ---- need to be modified according to your database ----
        String dbSourceUrl = "jdbc:mysql://192.168.3.60:3306/seeho_test_service?useSSL=false&serverTimezone=Asia/Shanghai";
        String dbUsername = "root";
        String dbPassword = "root321";

        String projectPath = System.getProperty("user.dir");

        // the next path need to be modified according to your project structure
        // ------------ start ------------
        String package_module = "seeho-service-test-persistence";
        String basePackage = "/src/main/java/com/seeho/service/test/persistence/";
        //------------ end ------------

        String filePerPath = projectPath + "/" + package_module + basePackage;

        String entryPath = filePerPath + "po";
        String mapperPath = filePerPath + "mapper";

        String mapperXmlPath = projectPath + "/" + package_module + "/src/main/resources/mapper";
        String servicePath = filePerPath + "IService";
        String serviceImplPath = filePerPath + "IService/impl";


        Map<OutputFile, String> pathInfoMap = new HashMap<>();
        pathInfoMap.put(OutputFile.entity, entryPath);
        pathInfoMap.put(OutputFile.mapper, mapperPath);
        pathInfoMap.put(OutputFile.xml, mapperXmlPath);
        pathInfoMap.put(OutputFile.service, servicePath);
        pathInfoMap.put(OutputFile.serviceImpl, serviceImplPath);

        FastAutoGenerator.create(
                        dbSourceUrl,
                        dbUsername,
                        dbPassword)
                .templateEngine(new VelocityTemplateEngine())
                .globalConfig(builder -> builder
                                .author("Leonpo")
                                .disableOpenDir()
                                .commentDate("yyyy-MM-dd")
                        //.outputDir(projectPath + "/src/main/java/com/seeho/service/test/persistence/mapper")
                )
                .packageConfig(builder -> builder
                        .parent("com.seeho.service.test.persistence")
                        .entity("po")
                        .mapper("mapper")
                        .service("IService")
                        .serviceImpl("IService.impl")
                        .pathInfo(pathInfoMap)
                )
                .strategyConfig(builder -> builder
                        //.addInclude("three_p_data") // if you need only generate this table, write this line
                        .addTablePrefix("")
                        .entityBuilder().enableLombok().formatFileName("%sPO").enableFileOverride()

                        .mapperBuilder()
                        .enableFileOverride()
                        .mapperAnnotation(org.apache.ibatis.annotations.Mapper.class)//加上@mapper注解
                        .superClass("com.baomidou.mybatisplus.core.mapper.BaseMapper")
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        .enableBaseResultMap()
                        .enableBaseColumnList()

                        .serviceBuilder()
                        .enableFileOverride()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .superServiceClass("com.baomidou.mybatisplus.extension.service.IService")
                        .superServiceImplClass("com.baomidou.mybatisplus.extension.service.impl.ServiceImpl")

                )
                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
