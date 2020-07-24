package com.bruis.oauth.mapper;

import com.bruis.common.backend.model.entity.PermissionDO;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface PermissionMapper extends MyMapper<PermissionDO> {
    List<PermissionDO> selectPermissionsByUserId(@Param("userId") Long userId);
}