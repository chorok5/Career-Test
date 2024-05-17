package com.kidwiz.web.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   
   @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedOriginPatterns("http://localhost") // Vue 앱의 도메인과 포트
        .allowedMethods("GET", "POST", "PUT", "DELETE")
        .allowCredentials(true);
    }
}