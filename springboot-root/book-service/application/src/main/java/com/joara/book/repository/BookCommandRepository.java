package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookCommandRepository extends BaseCommandRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);  // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)
    
    boolean existsBookByTitle(String title);
    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044)

    boolean update(String title, String description, BookStatus status, Long bookId);

    void deleteById(Long id);
}
