package com.joara.exception;

import com.joara.exception.status2xx.NoContentException;
import com.joara.support.exception.CustomException;
import com.joara.support.exception.ErrorCode;
import com.joara.support.exception.ResponseError;
import com.joara.support.exception.ResponseError.SubError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public final class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class) // 하위 클래스도 포함됨.
    public ResponseEntity<ResponseError> handleCustomException(CustomException exception) {
        ErrorCode errorCode = exception.getErrorCode();

        HttpStatus httpStatus = errorCode.defaultHttpStatus();
        ResponseError response = ResponseError.of(
                errorCode,
                getPlattedSubErrors(exception.getCause()));

        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<?> handleNoContentException(NoContentException exception) {
        return ResponseEntity.noContent().build();
    }   // 서브 에러를 중첩 구조에서 1차원 배열 구조로 펼치는 메서드 예시.

    private SubError[] getPlattedSubErrors(Throwable cause) {
        Throwable currentCause = cause;
        List<SubError> subErrors = new ArrayList<>();

        while (currentCause != null) {
            String errorFullName = currentCause.getClass().getSimpleName();
            String field = errorFullName.substring(errorFullName.lastIndexOf('.') + 1);
            subErrors.add(
                    SubError.builder()
                            .field(field)
                            .message(currentCause.getLocalizedMessage())
                            .build()
            );
            currentCause = currentCause.getCause();
        }

        SubError[] subErrorArray = new SubError[subErrors.size()];
        return subErrors.toArray(subErrorArray);
    }
}
