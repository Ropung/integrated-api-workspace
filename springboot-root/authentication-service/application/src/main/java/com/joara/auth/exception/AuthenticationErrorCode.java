package com.joara.auth.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthenticationErrorCode implements ErrorCode {
	MISMATCHED("회원 정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
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
