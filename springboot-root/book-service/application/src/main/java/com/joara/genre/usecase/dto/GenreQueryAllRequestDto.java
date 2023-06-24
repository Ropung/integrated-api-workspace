package com.joara.genre.usecase.dto;

import com.joara.book.domain.model.genre.Genre;
import lombok.Builder;

import java.util.List;

@Builder
public record GenreQueryAllRequestDto(List<Genre> genres) {
}
