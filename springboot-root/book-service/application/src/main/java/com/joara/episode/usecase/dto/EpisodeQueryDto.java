package com.joara.episode.usecase.dto;

import com.joara.book.domain.model.episode.Episode;
import lombok.Builder;

import java.util.List;

public record EpisodeQueryDto() {
   public record EpisodeListResponseDto(
           List<Episode> episodeList
   ){}
}
