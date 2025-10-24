package com.seeho.service.test.facade;

import com.seeho.service.test.model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seeho-service-test")
public interface UserQueryFacade {

    @GetMapping("/userQueryFacade/selectUserById")
    UserDTO selectUserById(@RequestParam("userId") Long userId);
}
