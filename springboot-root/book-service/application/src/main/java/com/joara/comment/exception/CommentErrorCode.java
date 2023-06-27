package com.joara.comment.exception;

import com.joara.support.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public enum CommentErrorCode implements ErrorCode {

    COMMENT_NOT_FOUND("댓글정보를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    COMMENT_CONTENT_NOT_FOUND("댓글내용이 비어있습니다.", HttpStatus.NOT_FOUND),
    DEFAULT("", HttpStatus.INTERNAL_SERVER_ERROR);


    CommentErrorCode(String s, HttpStatus httpStatus) {
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