package com.seeho.service.test.facade.impl;

import com.seeho.service.test.domain.sendBill.BillQueryService;
import com.seeho.service.test.facade.SendBillFacade;
import com.seeho.service.test.model.SendaBilDTO;
import com.seeho.service.test.persistence.po.SendaBillsPO;
import com.seeho.service.test.service.util.BeanUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Leonpo
 * @since 2025-10-28
 */
@RestController
@RequestMapping("/sendBillFacade")
public class SendBillFacadeImpl implements SendBillFacade {

    @Resource
    public BillQueryService billQueryService;

    @GetMapping("/selectBillByBillCode")
    @Override
    public SendaBilDTO selectBillByBillCode(@RequestParam("billCode") String billCode) {

        SendaBillsPO sendaBillsPO = billQueryService.selectBillByBillCode(billCode);
        return BeanUtil.copy(sendaBillsPO, SendaBilDTO.class);
    }
}
