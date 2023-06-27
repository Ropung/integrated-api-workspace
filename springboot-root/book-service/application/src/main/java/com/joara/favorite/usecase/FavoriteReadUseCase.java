package com.joara.favorite.usecase;

import com.joara.favorite.usecase.dto.FavoriteQueryDto.FavoriteListResponseDto;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;

public interface FavoriteReadUseCase{
    FavoriteListResponseDto findbyMemberId(HttpServletRequest request, Pageable pageable);
}
