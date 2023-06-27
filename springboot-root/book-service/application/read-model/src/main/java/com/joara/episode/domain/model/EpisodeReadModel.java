package com.joara.episode.domain.model;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EpisodeReadModel() {
    @Builder
    public record EpisodeListViewReadModel(
            UUID id,
            Long bookId,
            Long epiNum,
            String nickname,
            String epiTitle,
            Long viewCount,
            Long heartCount,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            OffsetDateTime deletedAt
    ){}
}


