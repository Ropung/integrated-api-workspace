package com.joara.auth.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MemberQueryErrorCode implements ErrorCode {
	NOT_FOUND("헉 존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
	DEFAULT("헉 서버 실수", HttpStatus.INTERNAL_SERVER_ERROR);
	
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
	public MemberQueryException defaultException() {
		return new MemberQueryException(this);
	}
	
	@Override
	public MemberQueryException defaultException(Throwable cause) {
		return new MemberQueryException(this, cause);
	}
}
