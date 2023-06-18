package com.joara.book.repository;

import com.joara.book.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookCommandJpaRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllBy(Pageable pageable);
}
