package com.seeho.web.test.controller;

import com.seeho.service.test.facade.UserQueryFacade;
import com.seeho.service.test.model.UserDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonpo
 * @since 2025-10-23
 */
@RestController("/hello")
public class HelloController {

    @Resource
    private UserQueryFacade userQueryFacade;

    @GetMapping("/index")
    public String index() {
        return "ni hao";
    }

    @GetMapping("/queryUserById")
    public UserDTO queryUserById(@RequestParam("userId") Long userId) {
        return userQueryFacade.selectUserById(userId);
    }
}
