package com.joara.web.favorite;


import com.joara.favorite.usecase.FavoriteCreateUseCase;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books/favorite")
public class FavoriteCommandApi {
    private final FavoriteCreateUseCase favoriteCreateUseCase;

    @PostMapping()
    public FavoriteCreateResponseDto create(
          @ModelAttribute @Valid FavoriteCreateRequestDto dto,
          HttpServletRequest request
    ){
        return favoriteCreateUseCase.create(dto, request);
    }
}
