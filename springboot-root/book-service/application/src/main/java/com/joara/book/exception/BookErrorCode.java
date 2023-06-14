package com.joara.book.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
	IMAGE_UPLOAD_FAILURE("파일 업로드에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
	SERVICE_UNAVAILABLE("연결된 작업 처리에서 문제가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
	BOOK_NOT_FOUND("작품정보가 일치하지 않습니다.", HttpStatus.NOT_FOUND),
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
	public BookException defaultException() {
		return new BookException(this);
	}
	
	@Override
	public RuntimeException defaultException(Throwable cause) {
		return new BookException(this, cause);
	}
}
