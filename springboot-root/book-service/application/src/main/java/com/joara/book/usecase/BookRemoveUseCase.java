package com.joara.book.usecase;

import com.joara.book.usecase.dto.BookCommandDto.BookRemoveRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookRemoveResponseDto;

public interface BookRemoveUseCase {
    BookRemoveResponseDto remove(BookRemoveRequestDto dto);
}
