package com.joara.book.repository;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookQueryRepository extends BaseCommandRepository<Book, Long> {
    Optional<String> findTitleById(Long bookId);
    Optional<BookDetailedViewReadModel> findDetailedViewById(Long bookId);
    Optional<BookListViewReadModel> findListViewItemById(Long bookId);

    boolean existsById(Long id);

    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044))

    Page<Book> findAll(Pageable pageable);  // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)

    Page<BookListViewReadModel> findAllByGenreIdAndTitleContainsIgnoreCaseAndStatusIn(Long id, String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewReadModel> findAllByGenreIdAndDescriptionContainsIgnoreCaseAndStatusIn(Long id, String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewReadModel> findAllByGenreIdAndNicknameContainsIgnoreCaseAndStatusIn(Long id, String keyword, List<BookStatus> readableStatus, Pageable pageable);
    Page<BookListViewReadModel> findAllByGenreIdAndStatusIn(Long id, List<BookStatus> readableStatus, Pageable pageable);

    Page<BookListViewReadModel> findBooksByMemberId(UUID memberId, Pageable pageable);
}
