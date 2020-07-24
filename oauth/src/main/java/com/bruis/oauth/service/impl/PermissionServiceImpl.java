package com.bruis.oauth.service.impl;

import com.bruis.api.service.PermissionService;
import com.bruis.common.backend.model.entity.PermissionDO;
import com.bruis.oauth.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> selectPermissionsByUserId(Long userId) {
        return permissionMapper.selectPermissionsByUserId(userId);
    }
}
