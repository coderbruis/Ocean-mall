package com.bruis.oauth.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.bruis.api.service.UserService;
import com.bruis.common.model.dataObject.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoHaiYang
 */
@Component
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserById(User user) {
        List<User> list = new ArrayList<>();
        list.add(new User("1","张三"));
        list.add(new User("2","李四"));
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(list));
        return "用户ID："+user.getId();
    }
}
