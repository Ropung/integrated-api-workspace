package com.joara.episode.usecase.mapper;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.usecase.dto.EpisodeCommandDto.EpisodeCreateRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpisodeDtoMapper {
	Episode from(EpisodeCreateRequestDto dto);
}
