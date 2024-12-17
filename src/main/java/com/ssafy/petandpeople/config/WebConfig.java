package com.ssafy.petandpeople.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 URL 경로에 대해 CORS 설정 적용
                .allowedOrigins("https://petandpeople.site")
                .allowedMethods("GET", "POST", "OPTIONS") // 허용할 HTTP 메서드
                .allowCredentials(true) // 쿠키 인증 허용
                .maxAge(3600); // Preflight 요청 캐싱 시간 (1시간)
    }


}

