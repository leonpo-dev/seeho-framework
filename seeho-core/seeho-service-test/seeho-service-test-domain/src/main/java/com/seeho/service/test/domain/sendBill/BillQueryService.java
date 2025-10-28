package com.seeho.service.test.domain.sendBill;

import com.seeho.service.test.persistence.po.SendaBillsPO;

public interface BillQueryService {


    SendaBillsPO selectBillByBillCode(String billCode);
}
