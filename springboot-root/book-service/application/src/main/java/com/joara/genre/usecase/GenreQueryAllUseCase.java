package com.joara.genre.usecase;

import com.joara.book.domain.model.genre.Genre;

import java.util.List;

public interface GenreQueryAllUseCase {
    List<Genre> findAll();
}
