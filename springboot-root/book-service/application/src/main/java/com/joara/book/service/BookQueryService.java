package com.joara.book.service;

import com.joara.book.domain.model.BookReadModels.AnalyzedBookReadModel;
import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.dto.BookQueryDto.AnalyzedBookResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.MyBookListRespnseDto;
import com.joara.book.usecase.mapper.BookDtoMapper;
import com.joara.clients.MemberQueryPort;
import com.joara.exception.status2xx.NoContentException;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookQueryService implements BookQueryUseCase {
    private final BookQueryRepository bookQueryRepository;
    private final BookDtoMapper mapper;
    private final JwtParser jwtParser;
    private final MemberQueryPort memberQueryPort;

    @Override
    public BookDetailedViewReadModel findById(Long bookId) {
        return bookQueryRepository.findDetailedViewById(bookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);
    }

    @Override
    public BookReadByOneResponseDto findBookById(Long bookId) {
        return BookReadByOneResponseDto.builder()
                .book(findById(bookId))
                .build();
    }

    @Override
    public BookReadByGenreResponseDto findBooksByGenreId(
            Long genreId, Pageable pageable, SearchType searchType, String keyword) {
        // 향상된 switch문 Java 14
        Page<BookListViewReadModel> bookSearchResult = switch (searchType) {
            case TITLE ->
                    bookQueryRepository.findAllByGenreIdAndTitleContainsIgnoreCase(genreId, keyword, pageable);
            case CONTENT ->
                    bookQueryRepository.findAllByGenreIdAndDescriptionContainsIgnoreCase(genreId, keyword, pageable);
            case MEMBER_NAME ->
                    bookQueryRepository.findAllByGenreIdAndNicknameContainsIgnoreCase(genreId, keyword, pageable);
            case NONE -> bookQueryRepository.findAllByGenreId(genreId, pageable);
        };

        long lastPageNumber = bookSearchResult.getTotalPages();
//        if (pageable.getPageNumber() >= lastPageNumber) {
//            throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
//        }
        if (bookSearchResult.isEmpty()) {
            throw new NoContentException();
        }

        List<BookListViewReadModel> bookList = bookSearchResult.toList();

        if (bookList.isEmpty()) {
            throw BookErrorCode.BOOK_NOT_FOUND.defaultException();
        }

        return BookReadByGenreResponseDto.builder()
                .bookList(bookList)
                .genreId(genreId)
                .lastPage(lastPageNumber)
                .build();
    }

    @Override
    public boolean verityAuthorAndOwnBook(UUID memberId, Long bookId) {
        UUID bookMemberId = bookQueryRepository
                .findById(bookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException)
                .memberId;

        return memberId == bookMemberId;
    }

    @Override
    public MyBookListRespnseDto findBookByMemberId(HttpServletRequest request, Pageable pageable) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.claims().getSubject();
        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();

        Page<BookListViewReadModel> bookSearchResult = bookQueryRepository.findBooksByMemberId(memberId, pageable);
        long lastPageNumber = bookSearchResult.getTotalPages();
        if (bookSearchResult.isEmpty()) {
            throw new NoContentException();
        }

        List<BookListViewReadModel> bookList = bookSearchResult.getContent();
        if (bookList.isEmpty()) {
            throw new NoContentException(); // 204 No Content
        }

        return MyBookListRespnseDto.builder()
                .bookList(bookList)
                .lastPage(lastPageNumber)
                .build();
    }

    @Override
    public AnalyzedBookResponseDto analyzedBook(Long bookId) {
        AnalyzedBookReadModel bookReadModel = bookQueryRepository.findAnalyzedBookById(bookId);

        return AnalyzedBookResponseDto.builder()
                .analyzedBook(bookReadModel)
                .build();
    }

    @Override
    public BookListViewReadModel findListViewItemById(Long id) {
        return bookQueryRepository.findListViewItemById(id)
                // 하나라도 없으면 예외처리 중. ->
                .orElse(null);
    }
}
