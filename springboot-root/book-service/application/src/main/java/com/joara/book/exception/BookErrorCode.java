package com.joara.book.exception;

import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum BookErrorCode implements ErrorCode {
	NO_GENRE_SELECTED("장르가 입력되지 않았습니다.", HttpStatus.BAD_REQUEST),
	FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
	DUPLICATED_BOOK_TITLE("동일한 작품이 이미 등록되어 있습니다.", HttpStatus.BAD_REQUEST),
	IMAGE_UPLOAD_FAILURE("파일 업로드에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
	SERVICE_UNAVAILABLE("연결된 작업 처리에서 문제가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
	BOOK_NOT_FOUND("작품정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	EPISODE_TITLE_NOT_FOUND("회차제목이 비어있습니다.", HttpStatus.NOT_FOUND),
	EPISODE_CONTENT_NOT_FOUND("회차내용이 비어있습니다.", HttpStatus.NOT_FOUND),
	EPISODE_NOT_FOUND("회차정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
	PAGE_OUT_OF_RANGE("페이지를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
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
