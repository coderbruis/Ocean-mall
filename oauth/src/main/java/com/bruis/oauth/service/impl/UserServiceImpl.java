package com.bruis.oauth.service.impl;

import com.bruis.api.service.UserService;
import com.bruis.common.backend.model.entity.UserDO;
import com.bruis.oauth.mapper.UserMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

/**
 * @author LuoHaiYang
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDO getByUserName(String username) {
        Example example = new Example(UserDO.class);
        example.createCriteria().andEqualTo("username", username);
        return userMapper.selectOneByExample(example);
    }
}
