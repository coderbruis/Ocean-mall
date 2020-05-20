package com.bruis.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * @author LuoHaiYang
 *
 * 开启授权服务器
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    /**
     * 设置jwt加密key
     */
    private static final String JWT_SIGNING_KEY = "jwt_MC43A6m0Xt9jUIV";

    /**
     * 认证方式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 自定义用户服务
     */
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 配置客户端对应授权方式及客户端密码
     * 当前使用内存模式
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("febs")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)  //支持GET  POST  请求获取token
                .userDetailsService(userDetailsService) //必须注入userDetailsService否则根据refresh_token无法加载用户信息
//                .exceptionTranslator(customWebResponseExceptionTranslator)
                .reuseRefreshTokens(true);  //开启刷新token
    }

    /**
     * 认证服务器的安全配置
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()") //isAuthenticated():排除anonymous   isFullyAuthenticated():排除anonymous以及remember-me
                .allowFormAuthenticationForClients();  //允许表单认证
    }


    /**
     * jwt令牌增强，添加加密key
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(JWT_SIGNING_KEY);
        return converter;
    }

    /**
     * 使用JWT存储令牌信息
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * token认证服务
     */
    @Bean
    public ResourceServerTokenServices tokenService() {
        // 授权服务和资源服务在统一项目内，可以使用本地认证方式，如果再不同工程，需要使用远程认证方式
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
