package com.joara.favorite.usecase.dto;

import com.joara.book.domain.model.book.MemberFavorBook;
import lombok.Builder;

import java.util.List;

public record FavoriteQueryDto() {

    @Builder
    public record FavoriteListResponseDto(
            List<MemberFavorBook> memberFavorBookList,
            Long lastPage
    ){}
}

