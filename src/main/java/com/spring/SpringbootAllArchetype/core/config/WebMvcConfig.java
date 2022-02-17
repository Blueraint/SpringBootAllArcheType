package com.spring.SpringbootAllArchetype.core.config;

import com.spring.SpringbootAllArchetype.core.handler.InterceptorHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AllArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    final InterceptorHandler interceptorHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptorHandler);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
