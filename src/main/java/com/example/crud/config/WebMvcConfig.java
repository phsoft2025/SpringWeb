package com.example.crud.config;

import com.example.crud.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC配置 - 注册拦截器
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                // 拦截所有接口
                .addPathPatterns("/**")
                // 排除不需要认证的路径（如登录、注册、健康检查等）
                .excludePathPatterns(
                        "/api/auth/**",    // 认证相关接口（登录/注册）
                        "/error",         // Spring Boot默认错误页
                        "/actuator/**"    // 健康检查端点
                );
    }
}
