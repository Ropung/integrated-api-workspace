package com.joara.book.usecase;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.MyBookListRespnseDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface BookQueryUseCase {
    BookDetailedViewReadModel findById(Long id);

    BookReadByOneResponseDto findBookById(Long id);

    BookReadByGenreResponseDto findBooksByGenreId(Long genreId, Pageable pageable, SearchType searchType, String keyword);

    boolean verityAuthorAndOwnBook(UUID memberId, Long bookId);

    MyBookListRespnseDto findBookByMemberId(HttpServletRequest request, Pageable pageable);
}
