package com.joara.episode.mapper;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpisodeEntityMapper extends BaseEntityMapper<Episode, EpisodeEntity> {
}
