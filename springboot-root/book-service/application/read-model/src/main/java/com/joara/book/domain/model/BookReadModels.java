package com.joara.book.domain.model;

import com.joara.book.domain.model.book.type.BookStatus;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

public record BookReadModels() {
    @Builder
    public record BookListViewReadModel(
            Long id,
            List<Long> genreIdList,
            // added
            List<String> genreNameList,
            String nickname,
            String title,
            String coverUrl
    ) {}

    @Builder
    public record BookDetailedViewReadModel(
            Long id,
            // Added
            List<Long> genreIdList,
            // added
            List<String> genreNameList,
            String nickname,
            String title,
            String description,
            String coverUrl,
            BookStatus status,
            Long totalViewCount,
            Long totalHeartCount,
            Long favorCount,
            OffsetDateTime createdAt,
            OffsetDateTime updatedAt,
            OffsetDateTime deletedAt
    ) {
    }
}
