package com.joara.episode.usecase;

import com.joara.book.domain.model.book.type.SearchType;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeViewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EpisodeReadUseCase {
    EpisodeListResponseDto findEpisodesByBookId(Long bid, Pageable pageable);

    EpisodeViewResponseDto findEpisodeByID(UUID eid);
}
