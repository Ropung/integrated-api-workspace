package com.joara.book.repository;

import com.joara.book.entity.BookGenreMapEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookGenreMapQueryJpaRepository extends JpaRepository<BookGenreMapEntity, UUID> {
    List<BookGenreMapEntity> findByBookId(Long bookId);
    List<BookGenreMapEntity> findByGenreId(Long genreId);
}