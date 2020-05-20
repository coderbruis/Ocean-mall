package com.bruis.api.order.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bruis.api.service.OrderService;

/**
 * @author LuoHaiYang
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public String getOrderId(String orderName) {
        return orderName + "的订单号为：xxx";
    }
}
