package com.joara.episode.usecase.dto;

import lombok.Builder;

public record EpisodeCommandDto() {
    public record EpisodeCreateRequestDto(
            Long epiNum,
            String epiTitle,
            String content,
            String quote
    ){}

    public record EpisodeUpdateRequestDto(
            String epiTitle,
            String content,
            String quote,
            String cover
    ){}

    @Builder
    public record EpisodeCreateResponseDto(
        boolean success
    ){}

    @Builder
    public record EpisodeUpdateResponseDto(
            boolean success
    ){}

    @Builder
    public record EpisodeDeleteResponseDto(
            boolean success
    ){}
}
