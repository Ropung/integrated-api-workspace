package com.joara.jwt.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum JwtParsingErrorCode implements ErrorCode {
    ACCESS_TOKEN_EXPIRED("인증토큰이 만료되었습니다.", HttpStatus.UNAUTHORIZED);

    public final String message;
    public final HttpStatus httpStatus;

    @Override
    public HttpStatus defaultHttpStatus() {
        return httpStatus;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public JwtParsingException defaultException() {
        return new JwtParsingException(this);
    }

    @Override
    public JwtParsingException defaultException(Throwable cause) {
        return new JwtParsingException(this, cause);
    }
}
