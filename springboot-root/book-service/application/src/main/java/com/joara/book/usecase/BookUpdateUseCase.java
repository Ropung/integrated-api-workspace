package com.joara.book.usecase;

import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyRequestDto;
import com.joara.book.usecase.dto.BookCommandDto.BookModifyResponseDto;

public interface BookUpdateUseCase {
    BookModifyResponseDto modify(BookModifyRequestDto dto);
    boolean modifyStatus(Long bookId, BookStatus status);
}
