package com.joara.heart.usecase.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record HeartCommandDto() {
    public record HeartCreateRequestDto(
            Long bookId,
            UUID epiId
    ){}
    @Builder
    public record HeartCreateResponseDto(
       boolean success
    ){}
    @Builder
    public record HeartRemoveResponseDto(
            boolean success
    ){}
}
