package com.joara.book.usecase.dto;

import com.joara.book.domain.model.book.Book;
import lombok.Builder;

public record BookQueryDto() {
    public record BookReadByOneRequestDto(
            Long id
    ){}

    @Builder
    public record BookReadByOneResponseDto(
            Book book
    ){}
}
