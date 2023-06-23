package com.joara.episode.mapper;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.projection.EpisodeQueryProjections.EpisodeListViewProjection;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EpisodeEntityMapper extends BaseEntityMapper<Episode, EpisodeEntity> {
    Episode toDomainList(EpisodeEntity entityList);
    EpisodeEntity toEntityList(Episode episodeList);

    EpisodeListViewReadModel toReadModel(EpisodeListViewProjection projection);
}
