package com.seeho.web.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonpo
 * @since 2025-10-23
 */
@RestController("/hello")
public class HelloController {

    @GetMapping("/index")
    public String index() {
        return "ni hao";
    }
}
