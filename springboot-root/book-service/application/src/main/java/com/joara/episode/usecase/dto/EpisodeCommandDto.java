package com.joara.episode.usecase.dto;

import com.joara.book.domain.model.episode.type.EpisodeStatus;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EpisodeCommandDto() {
    public record EpisodeCreateRequestDto(
            String epiTitle,
            String content,
            String quote,
            String cover
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
