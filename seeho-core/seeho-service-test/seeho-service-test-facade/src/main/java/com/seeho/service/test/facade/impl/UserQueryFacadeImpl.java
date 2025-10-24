package com.seeho.service.test.facade.impl;

import com.seeho.service.test.facade.UserQueryFacade;
import com.seeho.service.test.model.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonpo
 * @since 2025-10-24
 */
@RestController
@RequestMapping("/userQueryFacade")
public class UserQueryFacadeImpl implements UserQueryFacade {

    @GetMapping("/selectUserById")
    @Override
    public UserDTO selectUserById(@RequestParam("userId") Long userId) {
        UserDTO userDTO = new UserDTO(100L, "admin");
        return userDTO;
    }
}
