package com.seeho.service.test;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * @author Leonpo
 * @since 2025-10-28
 */
public class ApplicationStart {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationStart.class, args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        System.out.println("---- Effective Datasource Config ----");
        System.out.println("driverClassName: " + env.getProperty("spring.datasource.driver-class-name"));
        System.out.println("url: " + env.getProperty("spring.datasource.url"));
        System.out.println("username: " + env.getProperty("spring.datasource.username"));
        System.out.println("password: " + env.getProperty("spring.datasource.password"));
        System.out.println("------------------------------------");
    }
}