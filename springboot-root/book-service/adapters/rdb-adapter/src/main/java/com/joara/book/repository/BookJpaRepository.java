package com.joara.book.repository;

import com.joara.book.entity._old.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {
    List<BookEntity> findAllBy(Pageable pageable);
}
