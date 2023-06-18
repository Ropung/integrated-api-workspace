package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryRepository extends BaseCommandRepository<Book, Long> {
    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044))
    Page<Book> findAll(Pageable pageable);  // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)
}
