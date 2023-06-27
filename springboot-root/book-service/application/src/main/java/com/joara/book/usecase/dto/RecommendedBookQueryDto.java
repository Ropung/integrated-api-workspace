package com.joara.book.usecase.dto;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import lombok.Builder;

import java.util.List;

public record RecommendedBookQueryDto() {
    @Builder
    public record RecommendedBooksQueryResponseDto(
            List<BookListViewReadModel> books
    ) {}
}
