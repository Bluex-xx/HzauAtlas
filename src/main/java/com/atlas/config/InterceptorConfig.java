package com.atlas.config;

import com.atlas.interceptors.JWTInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns=new ArrayList<>();
        patterns.add("/csi");
        registry.addInterceptor(new JWTInterceptor())
                .excludePathPatterns("/**");
    }
}
