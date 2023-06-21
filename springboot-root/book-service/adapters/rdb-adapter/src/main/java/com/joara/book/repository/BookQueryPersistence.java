package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.mapper.BookEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookQueryPersistence implements BookQueryRepository {
    private final BookQueryJpaRepository bookQueryJpaRepository;
    private final BookEntityMapper mapper;

    @Override
    public Page<Book> findAll(Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Book save(Book domain) {
        // TODO
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookQueryJpaRepository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAllByGenreIdAndTitleContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookEntity> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndTitleContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAllByGenreIdAndDescriptionContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookEntity> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndDescriptionContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAllByGenreIdAndNicknameContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookEntity> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndNicknameContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAllByGenreId(Long id, Pageable pageable) {
        Page<BookEntity> bookEntities = bookQueryJpaRepository
                .findAllByGenreId(id, pageable);
        return bookEntities.map(mapper::toDomain);
    }
}
