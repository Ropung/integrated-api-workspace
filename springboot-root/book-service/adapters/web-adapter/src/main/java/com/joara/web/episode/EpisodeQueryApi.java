package com.joara.web.episode;

import com.joara.episode.usecase.EpisodeReadUseCase;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bid}/episode")
public class EpisodeQueryApi {

    private final EpisodeReadUseCase episodeReadUseCase;
    @GetMapping("")
    public EpisodeListResponseDto findEpisodesByBookId(
            @PathVariable Long bid,
            @PageableDefault(size=20, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst(); // 0, 1 <--
        return episodeReadUseCase.findEpisodesByBookId(bid, pageable);
    }

    @GetMapping("/{epiNum}")
    public EpisodeViewResponseDto findEpisodeByEpiNum(@PathVariable Long epiNum){
        return episodeReadUseCase.findEpisodeByEpiNum(epiNum);
    }

}
