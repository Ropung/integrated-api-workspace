package com.joara.book.domain.model;

import com.joara.book.domain.model.book.type.BookStatus;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record BookDetailedViewReadModel(
        Long genreId,
        // added
        String genreName,
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
