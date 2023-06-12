package com.joara.auth.utils.jwt;

import com.joara.auth.properties.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public final class JwtProvider {
	
	
	private final Key secretKey;
	private final Long expiredIn;
	
	
	public JwtProvider(JwtProperties jwtProperties) {
		byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
		secretKey = Keys.hmacShaKeyFor(keyBytes);
		expiredIn = jwtProperties.expiredIn();
	}
	
	public String generate(String email) {
		return generate(email, "USER");
	}
	
	public String generate(String email, String... roles) {
		Claims claims = Jwts.claims().setSubject(email);
		
		Date now = new Date();
		Date expireAt = new Date(now.getTime() + expiredIn);
		
		claims.put("roles", roles);
		
		return Jwts.builder()
				.setClaims(claims)
				.setIssuedAt(now)
				.setExpiration(expireAt)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
	}
}
