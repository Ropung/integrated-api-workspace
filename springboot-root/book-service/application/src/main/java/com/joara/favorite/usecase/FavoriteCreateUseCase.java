package com.joara.favorite.usecase;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface FavoriteCreateUseCase {
    FavoriteCreateResponseDto create(FavoriteCreateRequestDto dto, HttpServletRequest request);
    boolean create(MemberFavorBook memberFavorBook, HttpServletRequest request);
}
