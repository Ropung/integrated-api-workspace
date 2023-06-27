package com.joara.favorite.usecase.mapper;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import org.mapstruct.Mapper;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface FavoriteBookDtoMapper {
    MemberFavorBook from(FavoriteCreateRequestDto dto, OffsetDateTime now);
}
