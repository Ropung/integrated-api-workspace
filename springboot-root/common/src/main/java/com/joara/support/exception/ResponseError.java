package com.joara.support.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ResponseError(
        String code,
        Integer status,
        String name,
        String message,
        @JsonInclude(Include.NON_EMPTY) List<SubError> subErrors,
        LocalDateTime timestamp
) {
    public ResponseError {
        // 생성자가 쓰일 때, 생성자의 파라미터에 대한 검토와 변경을 수행하는 공간.
        if (code == null) code = "API_ERROR";
        if (status == null) status = 500;
        if (name == null) name = "ApiError";
        if (message == null || "".equals(message)) message = "API 사용 중 서버에서 오류가 발생했습니다.";
        if (timestamp == null) timestamp = LocalDateTime.now();
    }

    @Builder
    public record SubError(String field, String message) {}

    public static ResponseError of(HttpStatus httpStatus) {
        return ResponseError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .build();
    }

    public static ResponseError of(HttpStatus httpStatus, SubError... subError) {
        List<SubError> subErrors = List.of(subError);

        return ResponseError.builder()
                .code(httpStatus.name())
                .status(httpStatus.value())
                .name(httpStatus.getReasonPhrase())
                .message(httpStatus.series().name())
                .subErrors(subErrors)
                .build();
    }

    public static ResponseError of(ErrorCode errorCode) {
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ResponseError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(errorCode.defaultMessage())
                .build();
    }

    public static ResponseError of(ErrorCode errorCode, SubError... subError) {
        List<SubError> subErrors = List.of(subError);
        String errorName = errorCode.defaultException().getClass().getName();
        errorName = errorName.substring(errorName.lastIndexOf('.') + 1);

        return ResponseError.builder()
                .code(errorCode.name())
                .status(errorCode.defaultHttpStatus().value())
                .name(errorName)
                .message(errorCode.defaultMessage())
                .subErrors(subErrors)
                .build();
    }

    public ResponseError appendSubErrors(SubError... subError) {
        return this.appendSubErrors(List.of(subError));
    }

    public ResponseError appendSubErrors(List<SubError> subErrors) {
        return ResponseError.builder()
                .timestamp(this.timestamp())
                .status(this.status())
                .name(this.name())
                .message(this.message())
                .subErrors(subErrors)
                .build();
    }
}