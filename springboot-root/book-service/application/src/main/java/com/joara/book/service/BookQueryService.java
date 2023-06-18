package com.joara.book.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.repository.BookQueryRepository;
import com.joara.book.usecase.BookQueryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookQueryService implements BookQueryUseCase {
    private final BookQueryRepository bookQueryRepository;

    @Override
    public Optional<Book> findById(Long id) {
        return bookQueryRepository.findById(id);
    }
}
