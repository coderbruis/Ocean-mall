package com.bruis.oauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author LuoHaiYang
 */
@Configuration
public class SwaggerAutoConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 因为swagger-ui.html 是在springfox-swagger-ui.jar里的，
     * 修改了路径后，Spring Boot不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面，
     * 所以需要修改springboot配置类，为swagger建立新的静态文件路径映射就可以了
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
