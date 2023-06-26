package com.joara.web.genre;

import com.joara.genre.usecase.GenreQueryAllUseCase;
import com.joara.genre.usecase.dto.GenreQueryAllRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor // final, not null
@RequestMapping("/genre")
public final class GenreQueryApi {

    private final GenreQueryAllUseCase genreQueryAllUseCase;

    @GetMapping("")
    public GenreQueryAllRequestDto findBookById(){
        return GenreQueryAllRequestDto.builder()
                .genres(genreQueryAllUseCase.findAll())
                .build();
    }

}
