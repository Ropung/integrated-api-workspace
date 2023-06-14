package com.joara.auth.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;

public class MemberQueryException extends CustomException {
	public MemberQueryException() {
		super();
	}

	public MemberQueryException(String message) {
		super(message);
	}

	public MemberQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public MemberQueryException(ErrorCode errorCode) {
		super(errorCode);
	}

	public MemberQueryException(ErrorCode errorCode, Throwable cause) {
		super(errorCode, cause);
	}
}
