package com.joara.book.repository;

import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookQueryJpaRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByMemberIdAndTitle(UUID memberId, String title);

    Page<BookEntity> findAllByGenreIdAndTitleContainsIgnoreCase(Long id, String keyword, Pageable pageable);
    Page<BookEntity> findAllByGenreIdAndDescriptionContainsIgnoreCase(Long id, String keyword, Pageable pageable);
    Page<BookEntity> findAllByGenreIdAndNicknameContainsIgnoreCase(Long id, String keyword, Pageable pageable);
    Page<BookListViewProjection> findAllByGenreId(Long id, Pageable pageable);
}
