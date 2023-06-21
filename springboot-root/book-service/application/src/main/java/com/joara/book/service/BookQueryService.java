package com.joara.book.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import com.joara.book.usecase.mapper.BookDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public BookReadByGenreResponseDto findBooksByGenreId(
            Long genreId, Pageable pageable, SearchType searchType, String keyword) {
        // 향상된 switch문 Java 14
        Page<Book> bookSearchResult = switch (searchType) {
            case TITLE ->
                    bookQueryRepository.findAllByGenreIdAndTitleContainsIgnoreCase(genreId, keyword, pageable);
            case CONTENT ->
                    bookQueryRepository.findAllByGenreIdAndDescriptionContainsIgnoreCase(genreId, keyword, pageable);
            case MEMBER_NAME ->
                    bookQueryRepository.findAllByGenreIdAndNicknameContainsIgnoreCase(genreId, keyword, pageable);
            case NONE -> bookQueryRepository.findAllByGenreId(genreId, pageable);
        };

        long lastPageNumber = bookSearchResult.getTotalPages();
        if (pageable.getPageNumber() >= lastPageNumber) {
            throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
        }

        List<Book> bookList = bookSearchResult.toList();

        if (bookList.isEmpty()) {
            throw BookErrorCode.BOOK_NOT_FOUND.defaultException();
        }

        return BookReadByGenreResponseDto.builder()
                .bookList(bookList)
                .genreId(genreId)
                .lastPage(lastPageNumber)
                .build();
    }
}
