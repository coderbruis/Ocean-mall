package com.bruis.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author LuoHaiYang
 *
 * 资源服务器配置
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "ALL";

    @Autowired
    private ResourceServerTokenServices tokenServices;

    /**
     * 验证令牌配置
     * RESOURCE_ID 必须和授权服务器中保持一致
     * @param config
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.resourceId(RESOURCE_ID)
                .tokenServices(tokenServices)
                .stateless(true);
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users/**").authenticated() //配置users访问控制，必须认证过后才可以访问
                .antMatchers("/test/**").permitAll() //配置test无须认证，可以匿名访问
                .antMatchers("/webjars/**", "/resources/**", "/swagger-ui.html"
                        , "/swagger-resources/**", "/v2/api-docs", "index.html").permitAll()
                .anyRequest().authenticated();
    }

}
