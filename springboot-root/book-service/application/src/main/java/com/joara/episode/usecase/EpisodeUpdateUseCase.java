package com.joara.episode.usecase;


import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateRequestDto;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeUpdateResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface EpisodeUpdateUseCase {
    EpisodeUpdateResponseDto update(Long bid, UUID eid, EpisodeUpdateRequestDto dto, HttpServletRequest request);
}
