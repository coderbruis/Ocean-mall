package com.bruis.api.gateway;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author LuoHaiYang
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@EnableSwagger2
// 开启权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
