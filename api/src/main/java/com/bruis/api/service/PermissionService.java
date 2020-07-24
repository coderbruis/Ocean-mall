package com.bruis.api.service;

import com.bruis.common.backend.model.entity.PermissionDO;

import java.util.List;

/**
 * @author LuoHaiYang
 */
public interface PermissionService {
    default List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return null;
    }
}
