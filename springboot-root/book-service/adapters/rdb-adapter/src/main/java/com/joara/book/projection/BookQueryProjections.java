package com.joara.book.projection;

import lombok.Builder;

public record BookQueryProjections() {
    @Builder
    public record BookListViewProjection(
            Long genreId,
            String genreKor,
            String nickname,
            String title,
            String coverUrl
    ) {}
}
