package com.joara.genre.service;

import com.joara.book.domain.model.genre.Genre;
import com.joara.genre.repository.GenreQueryRepository;
import com.joara.genre.usecase.GenreQueryAllUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreQueryService implements GenreQueryAllUseCase {

    private final GenreQueryRepository genreQueryRepository;
    @Override
    public List<Genre> findAll() {
        return genreQueryRepository.findAll();
    }
}
