package com.joara.book.domain.model;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import lombok.Builder;

import java.util.List;

public record RecommendedBookReadModels() {
    @Builder
    public record RecommendedBooksReadModel(
            List<BookListViewReadModel> recommendedBooks
    ) {}
}
