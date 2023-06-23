package com.joara.genre.repository;

import com.joara.book.domain.model.genre.Genre;

import java.util.List;

public interface GenreQueryRepository {
    List<Genre> findAll();
}
