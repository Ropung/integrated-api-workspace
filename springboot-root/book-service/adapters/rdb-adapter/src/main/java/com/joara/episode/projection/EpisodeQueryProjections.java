package com.joara.episode.projection;

import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.UUID;

public record EpisodeQueryProjections() {
    @Builder
    public record EpisodeListViewProjection(
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
