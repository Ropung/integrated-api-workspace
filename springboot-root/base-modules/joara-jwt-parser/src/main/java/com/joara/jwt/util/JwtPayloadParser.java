package com.joara.jwt.util;

import io.jsonwebtoken.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
public final class JwtPayloadParser {
	private final JwtParser jwtParser;
	private final HttpServletRequest request;
	
	public String subject() {
		return claims().getSubject();
	}
	
	public Claims claims() {
		String token = request.getHeader("Authorization");
		
		if (token == null || !token.startsWith("Bearer ")) {
			// FIXME
			throw new AuthenticationCredentialsNotFoundException("현종아, Bearer 헤더에 토큰을 넣어줘 로그인이 안 돼있어");
		}
		
		token = token.substring(7);
		return new Claims(jwtParser.parseClaimsJws(token).getBody());
	}
}
