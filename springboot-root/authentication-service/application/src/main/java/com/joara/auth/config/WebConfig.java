package com.joara.auth.config;

// 편하게 쓰려고 설정 파일 작성, 따로 분리 (설정,프로퍼티스)

import com.joara.auth.properties.cors.CorsProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	
	protected final CorsProperties corsProperties;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins(corsProperties.allowed().origins())
				.allowedMethods(corsProperties.allowed().methods())
				.allowedHeaders(corsProperties.allowed().headers())
				.allowCredentials(corsProperties.allowed().credentials()) // for "Access-Control-Allow-Credentials"
				.exposedHeaders(corsProperties.exposedHeaders())
				.maxAge(3600L); // seconds
	}
}
