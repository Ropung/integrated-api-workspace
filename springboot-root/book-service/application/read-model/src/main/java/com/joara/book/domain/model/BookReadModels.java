package com.joara.book.domain.model;

import com.joara.book.domain.model.book.type.BookStatus;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

public record BookReadModels() {
    @Builder
    public record BookListViewReadModel(
            Long id,
            Long genreId,
            // added
            String genreName,
            String nickname,
            String title,
            String coverUrl
    ) {}

    @Builder
    public record BookDetailedViewReadModel(
            Long id,
            // Added
            List<Long> genreId,
            // added
            List<String> genreName,
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
