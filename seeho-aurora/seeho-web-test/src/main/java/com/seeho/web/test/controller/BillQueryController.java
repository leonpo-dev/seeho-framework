package com.seeho.web.test.controller;

import com.seeho.service.test.facade.SendBillFacade;
import com.seeho.service.test.model.SendaBilDTO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonpo
 * @since 2025-10-28
 */
@RestController("/billQuery")
public class BillQueryController {

    @Resource
    private SendBillFacade sendBillFacade;


    @GetMapping("/queryBillByBillCode")
    public SendaBilDTO queryBillByBillCode(@RequestParam("billCode") String billCode) {
        return sendBillFacade.selectBillByBillCode(billCode);
    }
}
