package com.joara.auth.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum OauthErrorCode implements ErrorCode {

	INVALID_EXTERNAL_TOKEN("인가되지 않은 외부토큰입니다.", HttpStatus.BAD_REQUEST),
	DEFAULT("서버 문제로 인증을 수행하지 못했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

	public final String message;
	public final HttpStatus status;
	
	@Override
	public HttpStatus defaultHttpStatus() {
		return status;
	}
	
	@Override
	public String defaultMessage() {
		return message;
	}
	
	@Override
	public AuthenticationException defaultException() {
		return new AuthenticationException(this);
	}
	
	@Override
	public AuthenticationException defaultException(Throwable cause) {
		return new AuthenticationException(this, cause);
	}
}
