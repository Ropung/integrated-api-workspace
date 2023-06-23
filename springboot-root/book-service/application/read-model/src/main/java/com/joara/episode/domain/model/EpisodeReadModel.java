package com.joara.episode.domain.model;

import lombok.Builder;

import java.time.OffsetDateTime;

public record EpisodeReadModel() {
    @Builder
    public record EpisodeListViewReadModel(
            Long bookId,
            String epiTitle,
            Long viewCount,
            Long heartCount,
            OffsetDateTime createdAt

    ){}
}


