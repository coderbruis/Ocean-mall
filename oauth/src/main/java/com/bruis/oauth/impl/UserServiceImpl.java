package com.bruis.oauth.impl;

import com.alibaba.fastjson.JSON;
import com.bruis.common.portal.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        UserDTO userDTO = new UserDTO();
        // 为了增强jwt令牌内容，可以将整个对象转json存放到username中
        // userDTO.setUsername(JSON.toJSONString(user));
        // userDTO.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM, user:select"));
        // userDTO.setPassword(passwordEncoder.encode("123"));

        //return loadFromDB(s);
        Map<String, String> userMap = new HashMap<>();
        userMap.put("admin", passwordEncoder.encode("123456789"));
        if (userMap.containsKey(s)) {
            UserDTO userDTOReturn = new UserDTO();
            userDTOReturn.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM, user:select"));
            userDTOReturn.setPassword(passwordEncoder.encode("123456789"));
            return userDTOReturn;
        }
        throw new UsernameNotFoundException("Username Not Found.");
    }

    private UserDTO loadFromDB(String username) throws UsernameNotFoundException {
        Map<String, String> userMap = new HashMap<>();
        userMap.put("admin", passwordEncoder.encode("123"));
        if (userMap.containsKey(username)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_NORMAL,ROLE_MEDIUM, user:select"));
            userDTO.setPassword(passwordEncoder.encode("123"));
            return userDTO;
        }
        throw new UsernameNotFoundException("Username Not Found.");
    }


}
