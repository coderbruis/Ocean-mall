package com.bruis.oauth.config.impl;

import com.bruis.api.service.PermissionService;
import com.bruis.api.service.UserService;
import com.bruis.common.backend.model.entity.PermissionDO;
import com.bruis.common.backend.model.entity.UserDO;
import com.bruis.common.portal.model.dto.UserDTO;
import com.google.common.collect.Lists;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LuoHaiYang
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserDO userDO = userService.getByUserName(s);
        List<GrantedAuthority> grantedAuthorities = Lists.newArrayList();

        if (userDO != null) {
            // 获取用户授权
            List<PermissionDO> permissionDOS = permissionService.selectPermissionsByUserId(userDO.getId());
            // 声明用户授权
            permissionDOS.forEach(permissionDO -> {
                if (permissionDO != null && permissionDO.getEnname() != null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionDO.getEnname());
                    grantedAuthorities.add(grantedAuthority);
                }
            });
        }

        return new UserDTO(userDO.getUsername(), userDO.getPassword(), grantedAuthorities);
    }

}
