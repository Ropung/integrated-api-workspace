package com.joara.book.usecase.dto;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import lombok.Builder;

import java.util.List;

public record BookQueryDto() {

    // @PathVariable로 bookId 받아서 주석
//    public record BookReadByOneRequestDto(
//            Long id
//    ){}

    @Builder
    public record BookReadByOneResponseDto(
            BookDetailedViewReadModel book
    ){}

    @Builder
    public record BookReadByGenreResponseDto(
            List<BookListViewReadModel> bookList,
            Long genreId,
            Long lastPage
    ){}

    @Builder
    public record MyBookListRespnseDto(
            List<BookDetailedViewReadModel> bookList,
            Long lastPage
    ){}
}
