package com.joara.jwt.config;

import com.joara.jwt.properties.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;

@Configuration
@RequiredArgsConstructor
public class Config {
	private final JwtProperties jwtProperties;
	
	@Bean
	public io.jsonwebtoken.JwtParser jjwtParser() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.password());
		Key secretKey = Keys.hmacShaKeyFor(keyBytes);
		
		return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build();
	}
}
