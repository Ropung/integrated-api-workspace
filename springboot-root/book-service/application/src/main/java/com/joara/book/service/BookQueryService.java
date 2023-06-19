package com.joara.book.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import com.joara.book.usecase.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookQueryService implements BookQueryUseCase {
    private final BookQueryRepository bookQueryRepository;
    private final BookDtoMapper mapper;

    @Override
    public Optional<Book> findById(Long id) {
        return bookQueryRepository.findById(id);
    }

    @Override
    public BookReadByOneResponseDto findBookById(Long id) {
        Book book =
                findById(id)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

        return BookReadByOneResponseDto.builder()
                .book(book)
                .build();
    }
}
