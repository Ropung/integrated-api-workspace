package com.joara.book.repository;


import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.entity.BookEntity;
import com.joara.book.entity.BookGenreMapEntity;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookCommandPersistence implements BookCommandRepository {
    // Delegation
     private final BookCommandJpaRepository bookCommandJpaRepository;
     private final BookGenreMapQueryJpaRepository bookGenreMapQueryJpaRepository;
     private final BookEntityMapper mapper;

    @Override
    public Book save(Book domain) {
        BookEntity entity = mapper.toEntity(domain);
        BookEntity savedEntity = bookCommandJpaRepository.save(entity);
        savedEntity.totalViewCount = 0L;
        savedEntity.totalHeartCount = 0L;
        savedEntity.favorCount = 0L;

        replaceBookGenreMaps(savedEntity.getId(), domain.genreIdList);

        return mapper.toDomain(savedEntity, domain.genreIdList);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookCommandJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        List<Book> books = bookCommandJpaRepository
                .findAllBy(pageable).stream()
                .map(mapper::toDomain)
                .toList();

        return new PageImpl<>(
                books,
                pageable,
                bookCommandJpaRepository.count() // TODO use after refactor, ... if necessary
        );
    }
    
    @Override
    public boolean existsBookByTitle(String title) {
        return false;
    }

    @Override
    @Transactional
    public boolean update(String title, List<Long> genreIdList,String description, BookStatus status, Long bookId) {
        BookEntity bookEntity = bookCommandJpaRepository.findById(bookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

        if (title != null && !"".equals(title)) {
            bookEntity.title = title;
        }

        if (description != null && !"".equals(description)) {
            bookEntity.description = description;
        }

        if (status != null) {
            bookEntity.status = status;
        }

        if (genreIdList != null && !genreIdList.isEmpty()) {
            replaceBookGenreMaps(bookId, genreIdList);
        }

        bookEntity.updatedAt = ServerTime.now();
        return true;
    }

    @Override
    public boolean update(Long bookId, BookStatus status) {
        return bookCommandJpaRepository.updateStatus(bookId, status) > 0;
    }

    @Override
    public boolean updateStatusAndDeletedAt(Long bookId, BookStatus status, OffsetDateTime deletedAt) {
        return 0 < bookCommandJpaRepository
                .updateStatusAndDeletedAt(bookId, BookStatus.REMOVED, deletedAt);
    }

    @Override
    public void deleteById(Long id) {
        bookCommandJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsNicknameByNickname(String nickname) {
        return bookCommandJpaRepository.existsNicknameByNickname(nickname);
    }

    private void replaceBookGenreMaps(Long bookId, List<Long> genreIdList) {
        List<BookGenreMapEntity> genreList = genreIdList.stream()
                .map((genreId) -> BookGenreMapEntity.builder()
                        .bookId(bookId)
                        .genreId(genreId)
                        .build()
                )
                .toList();

        bookGenreMapQueryJpaRepository.deleteAllByBookId(bookId);
        bookGenreMapQueryJpaRepository.saveAllAndFlush(genreList);
    }
}
