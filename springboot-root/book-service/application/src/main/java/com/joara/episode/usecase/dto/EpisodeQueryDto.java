package com.joara.episode.usecase.dto;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import lombok.Builder;

import java.util.List;


public record EpisodeQueryDto() {
   @Builder
   public record EpisodeListResponseDto(
           List<EpisodeListViewReadModel> episodeList,
           Long lastPage

   ){}
}
