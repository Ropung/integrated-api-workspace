package com.joara.book.properties.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;

@ConfigurationProperties(prefix = "app.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
		//1. 토큰 Properties에서 jwt 비밀번호를 가져온다.
		String password
) {
	public JwtProperties {
		if (password == null) password = "77d1b474a0920ab13e44a05170117cf0e809bad5c554d19020a95b45e9e2fb95893b8b149382e294d78fdb8e5aa2ae266b5797d985f5dc127366d2a50ec3938e";
	}
}