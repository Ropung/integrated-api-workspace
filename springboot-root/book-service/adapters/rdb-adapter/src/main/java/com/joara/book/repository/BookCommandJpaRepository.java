package com.joara.book.repository;

import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.entity.BookEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookCommandJpaRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findAllBy(Pageable pageable);
    boolean existsNicknameByNickname(String nickname);

    @Query("""
            update  BookEntity book
            set     book.status = :status
            where   book.id = :bookId
            """)
    @Transactional
    @Modifying
    Integer updateStatus(Long bookId, BookStatus status);
}
