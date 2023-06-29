package com.joara.book.repository;

import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.entity.BookEntity;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.book.projection.BookQueryProjections.BookTitleProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookQueryJpaRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookTitleProjection> findBookEntityById(Long bookId);
    Optional<BookDetailedViewProjection> findDetailedProjectionById(Long bookId);
    boolean existsByMemberIdAndTitle(UUID memberId, String title);
    Optional<BookListViewProjection> findListViewItemById(Long id);

    Page<BookListViewProjection> findAllByTitleContainsIgnoreCaseAndStatusIn(String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewProjection> findAllByDescriptionContainsIgnoreCaseAndStatusIn(String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewProjection> findAllByNicknameContainsIgnoreCaseAndStatusIn(String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewProjection> findAllByIdInAndStatusIn(List<Long> bookIdList, List<BookStatus> readableStatus, Pageable pageable);

    Page<BookListViewProjection> findBooksByMemberId(UUID memberId, Pageable pageable);

    BookDetailedViewProjection findScoreById(Long id);
}
