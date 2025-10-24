package com.seeho.service.test.domain.user;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DebugCheck {
    @Value("${spring.application.name:NULL}")
    private String appName;

    @PostConstruct
    public void init() {
        System.out.println("🟢 Spring Application Name = " + appName);
    }
}