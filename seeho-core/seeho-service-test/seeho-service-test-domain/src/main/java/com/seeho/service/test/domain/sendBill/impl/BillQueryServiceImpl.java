package com.seeho.service.test.domain.sendBill.impl;

import com.seeho.service.test.domain.sendBill.BillQueryService;
import com.seeho.service.test.persistence.IService.SendaBillsService;
import com.seeho.service.test.persistence.po.SendaBillsPO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author Leonpo
 * @since 2025-10-28
 */
@Service
public class BillQueryServiceImpl implements BillQueryService {

    @Resource
    private SendaBillsService sendaBillsService;

    @Override
    public SendaBillsPO selectBillByBillCode(String billCode) {
        return sendaBillsService.lambdaQuery()
                .eq(SendaBillsPO::getBillCode, billCode)
                .one();

    }
}
