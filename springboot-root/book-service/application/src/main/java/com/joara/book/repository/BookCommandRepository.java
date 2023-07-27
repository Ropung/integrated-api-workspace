package com.joara.book.repository;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.OffsetDateTime;
import java.util.List;

public interface BookCommandRepository extends BaseCommandRepository<Book, Long> {
    
    // Query    
 
    Page<Book> findAll(Pageable pageable);
    
    boolean existsBookByTitle(String title);

    // Command
    boolean update(String title, List<Long> genreIdList, String description, BookStatus status, Long bookId);
    boolean update(Long bookId, BookStatus status);
    boolean updateStatusAndDeletedAt(Long bookId, BookStatus status, OffsetDateTime deletedAt);

    void deleteById(Long id);

    boolean existsNicknameByNickname(String nickname);
}
