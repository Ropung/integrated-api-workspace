package com.joara.web.book;

import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneRequestDto;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByOneResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor // final, not null
@RequestMapping("/books")
public final class BookQueryApi {

    private final BookQueryUseCase bookQueryUseCase;

    @GetMapping("/{bookId}") //  ~/books/1
    public BookReadByOneResponseDto findBookById(@PathVariable Long bookId){
        return bookQueryUseCase.findBookById(bookId);
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
