package com.joara.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtHandler;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

// jjwt를 다른 모듈에서 받지않아도 이 모듈을 받으면
// 위임으로 구현된 이 대체제 JWT parser를 사용 가능
@Component
@RequiredArgsConstructor
public class JwtParser {
	 private final io.jsonwebtoken.JwtParser delegator;
	
	public boolean isSigned(String jwt) {
		return delegator.isSigned(jwt);
	}
	
	public Jwt parse(String jwt) throws ExpiredJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parse(jwt);
	}
	
	public <T> T parse(String jwt, JwtHandler<T> handler) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parse(jwt, handler);
	}
	
	public Jwt<Header, String> parsePlaintextJwt(String plaintextJwt) throws UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parsePlaintextJwt(plaintextJwt);
	}
	
	public Jwt<Header, Claims> parseClaimsJwt(String claimsJwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parseClaimsJwt(claimsJwt);
	}
	
	public Jws<String> parsePlaintextJws(String plaintextJws) throws UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parsePlaintextJws(plaintextJws);
	}
	
	public Jws<Claims> parseClaimsJws(String claimsJws) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return delegator.parseClaimsJws(claimsJws);
	}

	public JwtPayloadParser withRequest(HttpServletRequest request) {
		return withBearer(request.getHeader("Authorization"));
	}

	public JwtPayloadParser withBearer(String bearerToken) {
		if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
			// FIXME
			throw new AuthenticationCredentialsNotFoundException("현종아, Bearer 헤더에 토큰을 넣어줘 로그인이 안 돼있어");
		}

		bearerToken = bearerToken.substring(7);

		return withAccessToken(bearerToken);
	}

	public JwtPayloadParser withAccessToken(String accessToken) {
		return new JwtPayloadParser(this, accessToken);
	}

	@RequiredArgsConstructor
	public final class JwtPayloadParser {
		private final JwtParser jwtParser;
		private final String accessToken;

		public String subject() {
			return claims().getSubject();
		}

		public com.joara.jwt.util.Claims claims() {
			return new com.joara.jwt.util.Claims(jwtParser.parseClaimsJws(accessToken).getBody());
		}
	}

}
