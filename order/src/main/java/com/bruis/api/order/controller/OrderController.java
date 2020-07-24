package com.bruis.api.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bruis.api.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoHaiYang
 */
@RestController
public class OrderController {

    @Reference
    private ProductService productService;

    @GetMapping("/user/getOrder/{orderId}")
    public String getOrder(@PathVariable("orderId") Integer orderId) {
        // return productService.getProductName(orderId);
        return "Test getOder()";
    }
}
