package com.joara.web.book;

import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.RecommendedBookQueryUseCase;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import com.joara.book.usecase.dto.BookQueryDto.MyBookListRespnseDto;
import com.joara.book.usecase.dto.RecommendedBookQueryDto.RecommendedBooksQueryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor // final, not null
@RequestMapping("/books")
public final class BookQueryApi {

    private final BookQueryUseCase bookQueryUseCase;
    private final RecommendedBookQueryUseCase recommendedBookQueryUseCase;

    @GetMapping("/{bookId}")
    public BookReadByOneResponseDto findBookById(@PathVariable Long bookId){
        return bookQueryUseCase.findBookById(bookId);
    }

    @GetMapping("/genre/{genreId}")
    public BookReadByGenreResponseDto findBookGenreById(
            @PathVariable Long genreId,
            @RequestParam(required = false, defaultValue = "NONE") SearchType searchType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size=20, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst(); // 0, 1 <--
        return bookQueryUseCase.findBooksByGenreId(genreId, pageable, searchType, keyword);
    }

    @GetMapping("/me")
    public MyBookListRespnseDto findBookByMemberId(
            HttpServletRequest request,
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst();
        return bookQueryUseCase.findBookByMemberId(request, pageable);
    }

    @GetMapping("/{bookId}/recommend")
    public RecommendedBooksQueryResponseDto findRecommendedBooks(
            @PathVariable @NotNull Long bookId
    ) {
        return RecommendedBooksQueryResponseDto.builder()
                .books(recommendedBookQueryUseCase.findRecommendedBooksByBookId(bookId))
                .build();
    }

//	private final BookQueryService bookQueryService;
//	@GetMapping(path = "/genre/{genreEng}")
//	public GetBooksResponseDto getBooksByGenre(
//			@PathVariable String genreEng,
//			@PageableDefault(size=12, sort="createdAt", direction = Sort.Direction.DESC)
//			Pageable pageable,
//			@RequestParam(required = false) String keyword,
//			@RequestParam(required = false, defaultValue = "NONE") SearchType searchType) {
//
//		pageable = pageable.previousOrFirst();
//
//		Page<BookListProjection> bookSearchResult = bookQueryService.searchWithGenreBy(
//				genreEng, searchType, keyword, pageable);
//		List<BookListProjection> books = bookSearchResult.toList();
//		long lastPageNumber = bookSearchResult.getTotalPages();
//
//		if (pageable.getPageNumber() >= lastPageNumber) {
//			throw BookQueryErrorCode.PAGE_OUT_OF_RANGE.defaultException();
//		}
//
//		return GetBooksResponseDto.builder()
//				.books(books)
//				.lastPage(lastPageNumber)
//				.build();
//	}

//	@GetMapping("/{memberId}")
//	public GetMemberBooksResDto getBooksByMember(
//			@PathVariable UUID memberId,
//			@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
//			Pageable pageable,
//			@RequestParam(required = false) String keyword,
//			@RequestParam(required = false) String genreEng,
//			@RequestParam(required = false, defaultValue = "NONE") SearchType searchType){
//
//		pageable = pageable.previousOrFirst();
//
//		Page<BookListProjection> memberBookSearchResult = bookQueryService.searchWithGenreBy(
//				genreEng, searchType, keyword, pageable);
//
//		List<BookListProjection> books = memberBookSearchResult.toList();
//		long lastPageNumber = memberBookSearchResult.getTotalPages();
//
//		if (pageable.getPageNumber() >= lastPageNumber) {
//			throw BookQueryErrorCode.PAGE_OUT_OF_RANGE.defaultException();
//		}
//
//		return GetMemberBooksResDto.builder()
//				.books(books)
//				.lastPage(lastPageNumber)
//				.build();
//	}
}
