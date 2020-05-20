package com.bruis.api.service;

import com.bruis.common.model.dataObject.User;

/**
 * @author LuoHaiYang
 */
public interface UserService {

    /**
     * 根据用户ID获取用户信息
     */
    public String getUserById(User user);
}
