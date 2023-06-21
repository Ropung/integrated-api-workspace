package com.joara.book.usecase.dto;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import lombok.Builder;

import java.util.List;

public record BookQueryDto() {

    // @PathVariable로 bookId 받아서 주석
//    public record BookReadByOneRequestDto(
//            Long id
//    ){}

    @Builder
    public record BookReadByOneResponseDto(
            Book book
    ){}

    @Builder
    public record BookReadByGenreResponseDto(
            List<BookListViewReadModel> bookList,
            Long genreId,
            Long lastPage
    ){}
}
