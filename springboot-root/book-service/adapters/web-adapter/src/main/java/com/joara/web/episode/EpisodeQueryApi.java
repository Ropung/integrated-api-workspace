package com.joara.web.episode;

import com.joara.book.domain.model.book.type.SearchType;
import com.joara.book.usecase.dto.BookQueryDto.BookReadByGenreResponseDto;
import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeReadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bid}/episode")
public class EpisodeQueryApi {

    private final EpisodeReadUseCase episodeReadUseCase;
    @GetMapping("")
    public EpisodeListResponseDto findEpisodesByBookId(
            @PathVariable Long bid,
            @RequestParam(required = false, defaultValue = "NONE") SearchType searchType,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size=20, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst(); // 0, 1 <--
        return episodeReadUseCase.findEpisodesByBookId(bid, pageable, searchType, keyword);
    }

}
