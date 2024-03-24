package com.nhnacademy.inkbridge.front.config;

import com.nhnacademy.inkbridge.front.interceptor.CategoryInterceptor;
import com.nhnacademy.inkbridge.front.jwt.interceptor.JwtInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * class: WebConfig.
 *
 * @author devminseo
 * @version 3/8/24
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final JwtInterceptor jwtInterceptor;
    private final CategoryInterceptor categoryInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/error");

        registry.addInterceptor(categoryInterceptor)
            .addPathPatterns("/**")
            .excludePathPatterns("/admin/**")
            .excludePathPatterns("/error");

    }
}
