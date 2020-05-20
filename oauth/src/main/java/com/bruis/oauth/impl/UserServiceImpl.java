package com.bruis.oauth.impl;

import com.alibaba.fastjson.JSON;
import com.bruis.common.model.dataObject.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author LuoHaiYang
 */
@Service(value = "userDetailsService")
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 这里需要写根据用户名查询用户逻辑。。。。。。
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setUsername("admin");
        user.setPhone("188103956897");
        user.setStatus(6);

        UserDTO userDTO = new UserDTO();
        // 为了增强jwt令牌内容，可以将整个对象转json存放到username中
        userDTO.setUsername(JSON.toJSONString(user));

        userDTO.setPassword(passwordEncoder.encode("123"));

        return userDTO;
    }


}
