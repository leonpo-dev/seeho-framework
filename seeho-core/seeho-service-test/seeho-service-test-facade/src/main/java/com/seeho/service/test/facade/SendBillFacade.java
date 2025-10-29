package com.seeho.service.test.facade;

import com.seeho.service.test.model.SendaBilDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seeho-service-test",
        contextId = "sendBillFacade")
public interface SendBillFacade {

    @GetMapping("/sendBillFacade/selectBillByBillCode")
    SendaBilDTO selectBillByBillCode(@RequestParam("billCode") String billCode);
}
