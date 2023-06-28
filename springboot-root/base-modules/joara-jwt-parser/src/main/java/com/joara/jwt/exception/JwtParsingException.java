package com.joara.jwt.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;

public class JwtParsingException extends CustomException {
    public JwtParsingException(String message) {
        super(message);
    }

    public JwtParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtParsingException(ErrorCode errorCode) {
        super(errorCode);
    }

    public JwtParsingException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
