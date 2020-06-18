package com.bruis.api.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bruis.api.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoHaiYang
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Reference
    ProductService productService;

    @ApiOperation("根据产品编号取产品名称")
    @GetMapping("/getProductName/{productId}")
    //@PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    @PreAuthorize("hasRole('ROLE_MEDIUM')")
    public String getProductName(@PathVariable("productId") Integer productId) {
        return productService.getProductName(productId);
    }
}
