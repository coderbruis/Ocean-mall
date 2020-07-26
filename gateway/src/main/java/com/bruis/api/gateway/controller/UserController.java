package com.bruis.api.gateway.controller;

import com.alibaba.fastjson.JSONObject;
import com.bruis.common.backend.model.response.CurrentUserInfoVo;
import com.bruis.common.response.CommonResponse;
import com.bruis.common.utils.AvatarUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LuoHaiYang
 */

@RestController
@RequestMapping("/user")
@PreAuthorize("hasAuthority('SystemUser')")
public class UserController {

    public static final String INTRODUCTION = "I am a super administrator";

    @ApiOperation("获取当前用户信息")
    @GetMapping("/currentUser")
    public CommonResponse getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Set<String> roles = new HashSet<>();
        roles.add(name);
        CurrentUserInfoVo currentUserInfoVo = new CurrentUserInfoVo(name, roles, AvatarUtils.COMMON_AVATAR, INTRODUCTION);
        return CommonResponse.success(currentUserInfoVo);
    }

}
