package com.bruis.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LuoHaiYang
 */
@SpringBootApplication
@EnableSwagger2
// 开启权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}
