package com.joara.book.usecase;

import com.joara.book.domain.model.book.Book;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneRequestDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;

import java.util.Optional;

public interface BookQueryUseCase {
    Optional<Book> findById(Long id);

    BookReadByOneResponseDto findBookById(Long id);
}
