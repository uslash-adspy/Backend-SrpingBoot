package com.example.adSpy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 허용
                .allowedOrigins("*") // 모든 출처 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                // --- 수정 시작 ---
                .allowCredentials(false) // 자격 증명 포함 요청 허용하지 않음 (또는 이 라인 제거)
                // --- 수정 끝 ---
                .maxAge(3600); // Pre-flight 요청 캐싱 시간 (초)
    }
}