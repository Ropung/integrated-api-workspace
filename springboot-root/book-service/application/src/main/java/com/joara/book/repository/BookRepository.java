package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
import com.joara.support.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookRepository extends BaseRepository<Book, UUID> {
    Page<Book> findAll(Pageable pageable);  // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)
    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044)
}
