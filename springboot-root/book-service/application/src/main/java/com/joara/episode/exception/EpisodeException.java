package com.joara.episode.exception;

import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;

public class EpisodeException extends CustomException {
    public EpisodeException() {
        super();
    }

    public EpisodeException(String message) {
        super(message);
    }

    public EpisodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EpisodeException(ErrorCode errorCode) {
        super(errorCode);
    }

    public EpisodeException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
