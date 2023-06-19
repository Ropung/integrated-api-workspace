package com.joara.web.book;

import com.joara.book.exception.BookErrorCode;
import com.joara.book.usecase.BookCreateUseCase;
import com.joara.book.usecase.BookEditUseCase;
import com.joara.book.usecase.BookQueryUseCase;
import com.joara.book.usecase.BookRemoveUseCase;
import com.joara.book.usecase.dto.BookCommandDto;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateResponseDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyResponseDto;
import com.joara.book.usecase.dto.BookCommandDto.BookRemoveRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookRemoveResponseDto;
import com.joara.clients.MemberQueryPort;
import com.joara.jwt.util.JwtParser;
import com.joara.web.util.AuthorVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public final class BookCommandApi {

	private final BookCreateUseCase bookCreateUseCase;
	private final BookEditUseCase bookEditUseCase;
	private final BookRemoveUseCase bookRemoveUseCase;
	private final BookQueryUseCase bookQueryUseCase;
	private final MemberQueryPort memberQueryPort;
	private final JwtParser jwtParser;
	private final AuthorVerifier authorVerifier;

	@PostMapping("")
	public BookCreateResponseDto create(
			@ModelAttribute @Valid BookCommandDto.BookCreateRequestDto dto,
			@RequestPart(value = "coverImage", required = false)
			MultipartFile file,
			HttpServletRequest request
	){
		return bookCreateUseCase.create(dto, file, request);
	}

	@PutMapping("")
	public BookModifyResponseDto update(
			@RequestBody @Valid BookModifyRequestDto body,
			HttpServletRequest request) {
		// BOOK DB에 있는 member ID랑 이 요청을 보낸 member ID가 일치하는지:
		if (!authorVerifier.verify(request, body.bookId())) {
			throw BookErrorCode.FORBIDDEN.defaultException();
		}

		return bookEditUseCase.modify(body);
	}

	@DeleteMapping("")
	public BookRemoveResponseDto delete(
			@RequestBody @Valid BookRemoveRequestDto body,
			HttpServletRequest request) {
		// 본인 책이 맞는지.
		if (!authorVerifier.verify(request, body.bookId())) {
			throw BookErrorCode.FORBIDDEN.defaultException();
		}

		return bookRemoveUseCase.remove(body);
	}
}
