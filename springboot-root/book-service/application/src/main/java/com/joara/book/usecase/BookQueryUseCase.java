package com.joara.book.usecase;

import com.joara.book.domain.model.book.Book;

import java.util.Optional;

public interface BookQueryUseCase {
    Optional<Book> findById(Long id);
}
