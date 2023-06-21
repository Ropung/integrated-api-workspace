package com.joara.book.usecase;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookQueryUseCase {
    Optional<Book> findById(Long id);

    BookReadByOneResponseDto findBookById(Long id);

    BookReadByGenreResponseDto findBooksByGenreId(Long genreId, Pageable pageable, String page, SearchType searchType, String keyword);
}
