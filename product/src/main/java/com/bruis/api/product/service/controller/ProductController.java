package com.bruis.api.product.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LuoHaiYang
 */
@RestController
public class ProductController {
    @GetMapping("/productInfo")
    public String productInfo() {
        return "Test productInfo!";
    }
}
