package com.seeho.serive.test.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "seeho-serive-test", path = "/feign/seeho/service/test", contextId = "userQueryFacade")
public interface UserQueryFacade {

    @GetMapping("/selectUserById")
    void selectUserById(Long userId);
}
