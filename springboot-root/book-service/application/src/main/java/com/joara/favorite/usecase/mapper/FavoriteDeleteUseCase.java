package com.joara.favorite.usecase.mapper;

import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface FavoriteDeleteUseCase {
    FavoriteDeleteResponseDto delete(FavoriteDeleteRequestDto dto, HttpServletRequest request);
}
