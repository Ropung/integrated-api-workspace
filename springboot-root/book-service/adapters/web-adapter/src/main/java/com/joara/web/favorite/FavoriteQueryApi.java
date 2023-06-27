package com.joara.web.favorite;

import com.joara.favorite.usecase.FavoriteReadUseCase;
import com.joara.favorite.usecase.dto.FavoriteQueryDto.FavoriteListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/favorite")
public class FavoriteQueryApi {

    private final FavoriteReadUseCase favoriteReadUseCase;

    @GetMapping()
    public FavoriteListResponseDto view(
            HttpServletRequest request,
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable

    ){
        pageable = pageable.previousOrFirst();
        return favoriteReadUseCase.findbyMemberId(request, pageable);
    }
}
