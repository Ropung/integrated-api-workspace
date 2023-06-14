package com.joara.web;

import com.joara.book.usecase.BookCreateUseCase;
import com.joara.book.usecase.dto.BookCommandDto;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("")
	public BookCreateResponseDto bookCreateResponseDto(
			@ModelAttribute @Valid BookCommandDto.BookCreateRequestDto dto,
			@RequestPart(value = "coverImage", required = false)
			MultipartFile file,
			HttpServletRequest request
	){
		return bookCreateUseCase.create(dto, file, request);
	}
}
