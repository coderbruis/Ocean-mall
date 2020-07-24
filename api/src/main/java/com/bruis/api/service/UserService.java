package com.bruis.api.service;


import com.bruis.common.backend.model.entity.UserDO;

/**
 * @author LuoHaiYang
 */
public interface UserService {
    default UserDO getByUserName(String username) {
        return null;
    }
}
