package com.joara.book.usecase;

import com.joara.book.usecase.dto.BookCommandDto.BookModifyRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyResponseDto;

public interface BookEditUseCase {
    BookModifyResponseDto modify(BookModifyRequestDto dto);
}
