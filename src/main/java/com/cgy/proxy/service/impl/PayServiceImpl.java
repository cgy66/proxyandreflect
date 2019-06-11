package com.cgy.proxy.service.impl;

import com.cgy.proxy.service.PayService;

public class PayServiceImpl implements PayService {
    public void pay() {
        System.out.println("支付中......");
        try {
            Thread.sleep(500);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("支付完成！");
    }
}
