package com.bruis.api.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bruis.api.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoHaiYang
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    OrderService orderService;

    @ApiOperation("根据产品名称获取订单号")
    @GetMapping("/getOrderId/{productName}")
    public String getOrderId(@PathVariable("productName") String productName) {
        return orderService.getOrderId(productName);
    }

}
