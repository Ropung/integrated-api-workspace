package com.joara.genre.repository;

import com.joara.book.entity.BookGenreMapEntity;
import com.joara.genre.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreQueryJpaRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findAllInGenreId(List<BookGenreMapEntity> genreIds);
}
