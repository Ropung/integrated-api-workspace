package com.joara.heart.usecase.mapper;

import com.joara.book.domain.model.episode.EpisodeHeart;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface HeartDtoMapper {
    EpisodeHeart from(Long bookId, UUID epiId);
}
