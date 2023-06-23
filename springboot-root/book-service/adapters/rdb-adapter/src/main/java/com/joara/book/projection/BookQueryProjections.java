package com.joara.book.projection;

import com.joara.book.domain.model.book.type.BookStatus;
import lombok.Builder;

import java.time.OffsetDateTime;

public record BookQueryProjections() {
    @Builder
    public record BookListViewProjection(
            Long id,
            String nickname,
            String title,
            String coverUrl,
            Double score
    ) {}

    @Builder
    public record BookDetailedViewProjection(
            Long id,
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
            OffsetDateTime deletedAt,
            Double score
    ) {}
}
