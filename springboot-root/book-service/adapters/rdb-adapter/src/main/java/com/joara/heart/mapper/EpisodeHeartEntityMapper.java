package com.joara.heart.mapper;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.heart.entity.EpisodeHeartEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EpisodeHeartEntityMapper extends BaseEntityMapper<EpisodeHeart, EpisodeHeartEntity> {

}
