package com.joara.book.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;

public class BookException extends CustomException {
	public BookException() {
		super();
	}
	
	public BookException(String message) {
		super(message);
	}
	
	public BookException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BookException(ErrorCode errorCode) {
		super(errorCode);
	}
	
	public BookException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
