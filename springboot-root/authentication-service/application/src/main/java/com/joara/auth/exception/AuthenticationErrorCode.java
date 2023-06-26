package com.joara.auth.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {
	MISMATCHED("회원 정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
	UNAUTHORIZED("인증 유지 정보가 존재하지 않습니다.", HttpStatus.UNAUTHORIZED),
	FORBIDDEN("올바르지 않은 권한입니다.", HttpStatus.FORBIDDEN),
	INVALID_TOKENS("올바르지 않은 인증 정보입니다.", HttpStatus.BAD_REQUEST),
	DEFAULT("", HttpStatus.INTERNAL_SERVER_ERROR);
	
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
	public RuntimeException defaultException(Throwable cause) {
		return new AuthenticationException(this, cause);
	}
}
