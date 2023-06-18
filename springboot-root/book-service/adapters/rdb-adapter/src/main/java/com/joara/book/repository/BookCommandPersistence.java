package com.joara.book.repository;


import com.joara.book.domain.model.book.Book;
import com.joara.book.entity._old.BookEntity;
import com.joara.book.mapper.BookEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BookCommandPersistence implements BookCommandRepository {
    // Delegation
     private final BookJpaRepository bookJpaRepository;
     private final BookEntityMapper mapper;

    @Override
    public Book save(Book domain) {
        // use mapper: Member -> MemberEntity
        // use mapper: MemberEntity -> Member
        BookEntity entity = mapper.toEntity(domain);
        BookEntity savedEntity = bookJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Book> findById(UUID uuid) {
        return bookJpaRepository.findById(uuid)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
//        return memberJpaRepository.findAll(pageable) // returns Page<MemberEntity> ... -> Low Performance
//                .map(mapper::toDomain);
        return new PageImpl<>(
                bookJpaRepository.findAllBy(pageable)
                        .stream()
                        .map(mapper::toDomain)
                        .toList(),
                pageable,
                bookJpaRepository.count() // TODO use after refactor, ... if necessary
        );
    }
    
    @Override
    public boolean existsBookByTitle(String title) {
        return false;
    }
}
