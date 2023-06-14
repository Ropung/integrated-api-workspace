package com.joara.book.usecase;

import com.joara.book.domain.model.book.Book;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookCreateResponseDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface BookCreateUseCase {
	BookCreateResponseDto create(BookCreateRequestDto dto, MultipartFile file, HttpServletRequest request);
	
	boolean create(Book book, MultipartFile file, HttpServletRequest request);
}
