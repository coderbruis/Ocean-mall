package com.bruis.oauth.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.bruis.api.service.ProductService;
import com.bruis.common.model.dataObject.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@Api(tags = "SystemController控制类")
@RestController
public class SystemController {

    @Reference
    private ProductService productService;

    /**
     * 访问时，携带token，进行验证
     */
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
    public String getOrder(@PathVariable("orderId") Integer orderId) {
        return productService.getProductName(222);
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

    /**
     * 获取当前登录用户信息
     *
     * @param authentication
     * @return
     */
    @RequestMapping("/info")
    public Object info(OAuth2Authentication authentication) {
        // 需要配合UserServiceImpl--》userDTO.setUsername(JSON.toJSONString(user))使用
        UserDTO ddo = JSON.parseObject(authentication.getPrincipal().toString(),UserDTO.class);

        // SecurityContextHolder 和 OAuth2Authentication都可以获取当前用户信息
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        return "获取到了用户资源："+ddo.getUsername()+"------"+ddo.getUsername()+"----"+ddo.getPhone();
    }

}
