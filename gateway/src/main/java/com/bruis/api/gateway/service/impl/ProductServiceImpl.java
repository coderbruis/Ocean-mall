package com.bruis.api.gateway.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bruis.api.service.ProductService;

/**
 * @author LuoHaiYang
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public String getProductName(Integer productId) {
        return "Dubbo: " + productId;
    }
}
