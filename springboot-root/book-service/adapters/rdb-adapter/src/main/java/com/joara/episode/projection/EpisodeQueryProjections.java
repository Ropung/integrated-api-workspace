package com.joara.episode.projection;

import lombok.Builder;

import java.time.OffsetDateTime;

public record EpisodeQueryProjections() {
    @Builder
    public record EpisodeListViewProjection(
        Long bookId,
        String epiTitle,
        Long viewCount,
        Long heartCount,
        OffsetDateTime createdAt

    ){}
}