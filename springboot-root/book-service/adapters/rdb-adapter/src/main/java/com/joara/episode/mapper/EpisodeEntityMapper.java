package com.joara.episode.mapper;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EpisodeEntityMapper extends BaseEntityMapper<Episode, EpisodeEntity> {
    List<Episode> toDomainList(List<EpisodeEntity> entityList);
    List<Episode> toEntityList(List<EpisodeEntity> domainList);
}
