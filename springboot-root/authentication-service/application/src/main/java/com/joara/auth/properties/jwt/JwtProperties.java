package com.joara.auth.properties.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "app.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
		//1. 토큰 Properties에서 jwt 비밀번호를 가져온다.
		String secret,
		Long expiredIn
) {
	public JwtProperties {
		if (expiredIn == null) expiredIn = 1_800_000L;
	}
}