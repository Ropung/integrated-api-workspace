package com.joara.episode.usecase;

import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeDeleteResponseDto;
import java.util.UUID;

public interface EpisodeDeleteUseCase {

    EpisodeDeleteResponseDto delete(Long bid, UUID eid);
}
