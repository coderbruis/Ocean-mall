package com.bruis.api.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bruis.api.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author LuoHaiYang
 */
@RestController
@RequestMapping("/order")
@PreAuthorize("hasAuthority('SystemOrder')")
public class OrderController {

    @Reference
    OrderService orderService;

    @ApiOperation("根据产品名称获取订单号")
    @GetMapping("/getOrderId/{productName}")
    public String getOrderId(@PathVariable("productName") String productName) {
        return orderService.getOrderId(productName);
    }

}
