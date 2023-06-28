package com.joara.book.repository;

import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookQueryJpaRepository extends JpaRepository<BookEntity, Long> {
    BookEntity findBookEntityById(Long bookId);
    Optional<BookDetailedViewProjection> findDetailedProjectionById(Long bookId);
    boolean existsByMemberIdAndTitle(UUID memberId, String title);
    Optional<BookListViewProjection> findListViewItemById(Long id);

    Page<BookListViewProjection> findAllByTitleContainsIgnoreCase(String keyword, Pageable pageable);
    Page<BookListViewProjection> findAllByDescriptionContainsIgnoreCase(String keyword, Pageable pageable);
    Page<BookListViewProjection> findAllByNicknameContainsIgnoreCase(String keyword, Pageable pageable);
    Page<BookListViewProjection> findAllByIdIn(List<Long> bookIdList, Pageable pageable);

    Page<BookDetailedViewProjection> findBooksByMemberId(UUID memberId, Pageable pageable);

    BookDetailedViewProjection findScoreById(Long id);
}
