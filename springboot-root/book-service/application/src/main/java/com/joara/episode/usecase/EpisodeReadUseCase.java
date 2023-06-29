package com.joara.episode.usecase;

import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeListResponseDto;
import com.joara.episode.usecase.dto.EpisodeQueryDto.EpisodeViewResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface EpisodeReadUseCase {
    EpisodeListResponseDto findEpisodesByBookId(Long bid, Pageable pageable);
    // 단건조회
    EpisodeViewResponseDto findEpisodeByEpiNum(Long bookId, Long epiNum);
    Optional<UUID> findMemberIdByEpisodeId(UUID episodeId);
}
