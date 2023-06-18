package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
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
    public Optional<Book> findById(Long aLong) {
        return bookQueryJpaRepository
                .findById(aLong)
                .map(mapper::toDomain);
    }
}
