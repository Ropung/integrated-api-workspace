package com.joara.reply.exception;

import com.joara.support.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum ReplyErrorCode implements ErrorCode {

    REPLY_NOT_FOUND("댓글정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    REPLY_CONTENT_NOT_FOUND("댓글내용이 비어있습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("", HttpStatus.INTERNAL_SERVER_ERROR);


    ReplyErrorCode(String s, HttpStatus httpStatus) {
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return null;
    }

    @Override
    public String defaultMessage() {
        return null;
    }

    @Override
    public RuntimeException defaultException() {
        return null;
    }

    @Override
    public RuntimeException defaultException(Throwable cause) {
        return null;
    }
}