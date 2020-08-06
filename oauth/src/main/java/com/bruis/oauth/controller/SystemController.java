package com.bruis.oauth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bruis.api.service.OrderService;
import com.bruis.api.service.ProductService;
import com.bruis.common.portal.model.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@RestController
public class SystemController {

    @Reference
    private ProductService productService;

    @Reference
    OrderService orderService;

    /**
     * 访问时，携带token，进行验证
     */
    @ApiOperation("测试获取用户列表")
    @GetMapping("/user/list")
    public List<UserDTO> getUser() {
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("张三","123456"));
        users.add(new UserDTO("李四","123456"));
        users.add(new UserDTO("张五","123456"));
        return users;
    }

    @ApiOperation("测试Dubbo远程调用")
    @GetMapping("/user/getOrder/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_MEDIUM2')")
    public String getOrder(@PathVariable("orderId") Integer orderId) {
        return productService.getProductName(222);
    }

    @PreAuthorize("hasAuthority('ROLE_MEDIUM')")
    @ApiOperation("根据产品名称获取订单号")
    @GetMapping("/getOrderId/{productName}")
    public String getOrderId(@PathVariable("productName") String productName) {
        return orderService.getOrderId(productName);
    }

    /**
     * 支持匿名访问，无须携带token
     */
    @GetMapping("/test/list")
    public List<UserDTO> getTestUser(){
        List<UserDTO> users = new ArrayList<>();
        users.add(new UserDTO("张三","123456"));
        users.add(new UserDTO("李四","123456"));
        users.add(new UserDTO("张五","123456"));
        return users;
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("/currentUser")
    public Authentication getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        return authentication;
    }

    /**
     * 获取当前登录用户信息
     *
     * @param authentication
     * @return
     */
    @GetMapping("/info")
    public Object info(OAuth2Authentication authentication) {
        // 需要配合UserServiceImpl--》userDTO.setUsername(JSON.toJSONString(user))使用
        UserDTO ddo = JSON.parseObject(authentication.getPrincipal().toString(),UserDTO.class);

        // SecurityContextHolder 和 OAuth2Authentication都可以获取当前用户信息
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return "获取到了用户资源："+ddo.getUsername()+"------"+ddo.getUsername()+"----"+ddo.getPhone();
    }

    /**
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public Object getUserInfo() {
        String[] roles = {"admin"};
        String introduction = "I am a super administrator";
        String avator = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        String name = "Super Admin";
        JSONObject result = new JSONObject();
        result.put("roles", roles);
        result.put("introduction", introduction);
        result.put("avatar", avator);
        result.put("name", name);
        result.put("code", 20000);
        return result;
    }

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(principal);
        return principal;
    }
}
