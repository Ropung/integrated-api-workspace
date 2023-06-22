package com.joara.book.repository;

import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
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
    public String findTitleByBookId(Long bookId) {
        BookEntity bookEntity = bookQueryJpaRepository.findBookEntityById(bookId);
        if (bookEntity != null) {
            return bookEntity.getTitle();
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return bookQueryJpaRepository.existsById(id);
    }

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
    public Page<BookListViewReadModel> findAllByGenreIdAndTitleContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndTitleContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toReadModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndDescriptionContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndDescriptionContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toReadModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndNicknameContainsIgnoreCase(Long id, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndNicknameContainsIgnoreCase(id, keyword, pageable);
        return bookEntities.map(mapper::toReadModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreId(Long id, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreId(id, pageable);
        return bookEntities.map(mapper::toReadModel);
    }
}
