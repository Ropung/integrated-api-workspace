package com.joara.episode.usecase;

import com.joara.book.domain.model.book.type.SearchType;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import org.springframework.data.domain.Pageable;

public interface EpisodeReadUseCase {
    EpisodeListResponseDto findEpisodesByBookId(Long bid, Pageable pageable, SearchType searchType, String keyword);
}
