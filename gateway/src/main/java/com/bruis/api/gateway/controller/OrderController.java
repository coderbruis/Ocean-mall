package com.bruis.api.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.bruis.api.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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

    @ApiOperation("获取当前用户信息")
    @GetMapping("/currentUser")
    public JSONObject getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JSONObject currentUser = new JSONObject();
        Set<String> roles = new HashSet<String>();
        String name = authentication.getName();
        roles.add(name);
        currentUser.put("roles", roles);
        currentUser.put("introduction", "I am a super administrator");
        currentUser.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        currentUser.put("name", name);
        JSONObject response = new JSONObject();
        response.put("code",20000);
        response.put("data", currentUser);
        return response;
    }

}
