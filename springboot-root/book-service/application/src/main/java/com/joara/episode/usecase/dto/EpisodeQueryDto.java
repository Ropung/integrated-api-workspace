package com.joara.episode.usecase.dto;

import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import lombok.Builder;

import java.util.List;
import java.util.UUID;


public record EpisodeQueryDto() {
   @Builder
   public record EpisodeListResponseDto(
           List<EpisodeListViewReadModel> episodeList,
           Long lastPage
   ){}

   @Builder
   public record EpisodeViewResponseDto(
           UUID id,
           String bookTitle,
           String epiTitle,
           String content,
           String quote
   ){}
}
