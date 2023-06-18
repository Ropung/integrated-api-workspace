package com.joara.book.repository;

import com.joara.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookQueryJpaRepository extends JpaRepository<BookEntity, Long> {
    boolean existsByMemberIdAndTitle(UUID memberId, String title);
}
