package com.joara.favorite.usecase.dto;

import lombok.Builder;

import java.util.UUID;

public record FavoriteCommandDto() {
    public record FavoriteCreateRequestDto(
            Long bookId
    ){}
    @Builder
    public record FavoriteCreateResponseDto(
       boolean success
    ){}

}
