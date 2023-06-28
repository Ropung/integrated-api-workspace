package com.joara.favorite.usecase.dto;

import lombok.Builder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public record FavoriteCommandDto() {
    public record FavoriteCreateRequestDto(
            Long bookId
    ){}
    @Builder
    public record FavoriteCreateResponseDto(
       boolean success
    ){}
    public record FavoriteDeleteRequestDto(
            @NotNull
            Long bookId
    ){}
    @Builder
    public record FavoriteDeleteResponseDto(
            boolean success
    ){}

}
