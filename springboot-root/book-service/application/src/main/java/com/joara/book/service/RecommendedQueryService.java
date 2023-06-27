package com.joara.book.service;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.RecommendedBookQueryUseCase;
import com.joara.clients.RecommendedBookQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public final class RecommendedQueryService implements RecommendedBookQueryUseCase {
    private final RecommendedBookQueryPort recommendedBookQueryPort;
    private final BookQueryUseCase bookQueryUseCase;

    @Override
    public List<BookListViewReadModel> findRecommendedBooksByBookId(Long bookId) {
        // 10 20 30 -- fast api csv
        // 10 30 -- master db
        List<Long> idList = recommendedBookQueryPort.findRecommendedBookIdsByBookId(bookId);

        return idList.stream()
                .map(bookQueryUseCase::findListViewItemById)
                .filter(Objects::nonNull)
                .toList();
    }
}
