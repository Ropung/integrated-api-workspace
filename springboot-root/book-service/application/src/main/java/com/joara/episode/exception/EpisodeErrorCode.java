package com.joara.episode.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum EpisodeErrorCode implements ErrorCode {
    EPISODE_ID_NOT_FOUND("유효하지 않은 에피소드 고유번호입니다.", HttpStatus.NOT_FOUND),
    BOOK_NOT_WRITABLE("글쓰기가 허용되지 않은 작품입니다.", HttpStatus.BAD_REQUEST),
    EPISODE_DEFAULT("", HttpStatus.INTERNAL_SERVER_ERROR);

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
    public EpisodeException defaultException() {
        return new EpisodeException(this);
    }

    @Override
    public EpisodeException defaultException(Throwable cause) {
        return new EpisodeException(this, cause);
    }
}
