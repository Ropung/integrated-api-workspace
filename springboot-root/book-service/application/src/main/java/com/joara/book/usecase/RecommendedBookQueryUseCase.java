package com.joara.book.usecase;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;

import java.util.List;

public interface RecommendedBookQueryUseCase {
    List<BookListViewReadModel> findRecommendedBooksByBookId(Long bookId);
}
