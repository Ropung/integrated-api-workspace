package com.joara.heart.usecase.dto;

import lombok.Builder;

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
}
