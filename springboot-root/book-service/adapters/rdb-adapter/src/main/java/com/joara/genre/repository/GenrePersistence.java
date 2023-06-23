package com.joara.genre.repository;

import com.joara.book.domain.model.genre.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GenrePersistence implements GenreQueryRepository {
    private final GenreQueryJpaRepository genreQueryJpaRepository;

    @Override
    public List<Genre> findAll() {
        return genreQueryJpaRepository.findAll().stream()
                .map((entity) ->
                        Genre.builder()
                                .id(entity.getId())
                                .kor(entity.kor)
                                .eng(entity.eng)
                                .build()
                )
                .toList();
    }
}
