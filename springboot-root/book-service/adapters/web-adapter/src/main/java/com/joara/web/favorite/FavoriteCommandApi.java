package com.joara.web.favorite;


import com.joara.book.exception.BookErrorCode;
import com.joara.clients.MemberQueryPort;
import com.joara.favorite.usecase.FavoriteCreateUseCase;
import com.joara.favorite.usecase.FavoriteReadUseCase;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateResponseDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteResponseDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteRequestDto;
import com.joara.favorite.usecase.mapper.FavoriteDeleteUseCase;
import com.joara.jwt.util.JwtParser;
import com.joara.util.validation.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books/favorite")
public class FavoriteCommandApi {
    private final FavoriteCreateUseCase favoriteCreateUseCase;
    private final FavoriteDeleteUseCase favoriteDeleteUseCase;

    @PostMapping()
    public FavoriteCreateResponseDto create(
          @ModelAttribute @Valid FavoriteCreateRequestDto dto,
          HttpServletRequest request
    ){
        return favoriteCreateUseCase.create(dto, request);
    }
    @DeleteMapping()
    public FavoriteDeleteResponseDto delete(
            @ModelAttribute @Valid FavoriteDeleteRequestDto dto,
            HttpServletRequest request
    ){

        return favoriteDeleteUseCase.delete(dto,request);
    }
}
