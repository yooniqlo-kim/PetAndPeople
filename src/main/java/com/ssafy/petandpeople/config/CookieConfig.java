package com.ssafy.petandpeople.config;

import jakarta.servlet.SessionCookieConfig;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
//            sessionCookieConfig.setDomain(".example.com"); // 모든 서브도메인에서 쿠키 유효
            sessionCookieConfig.setPath("/");             // 모든 경로에서 쿠키 유효
        };
    }
}
