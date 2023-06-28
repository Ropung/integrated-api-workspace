package com.joara.book.repository;


import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.entity.BookEntity;
import com.joara.book.entity.BookGenreMapEntity;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookCommandPersistence implements BookCommandRepository {
    // Delegation
     private final BookCommandJpaRepository bookCommandJpaRepository;
     private final BookGenreMapQueryJpaRepository bookGenreMapQueryJpaRepository;
     private final BookEntityMapper mapper;

    @Override
    public Book save(Book domain) {
        BookEntity entity = mapper.toEntity(domain);
        BookEntity savedEntity = bookCommandJpaRepository.save(entity);
        savedEntity.totalViewCount = 0L;
        savedEntity.totalHeartCount = 0L;
        savedEntity.favorCount = 0L;
        List<BookGenreMapEntity> genreList = domain.genreIdList.stream()
                .map((genreId) -> BookGenreMapEntity.builder()
                        .bookId(savedEntity.getId())
                        .genreId(genreId)
                        .build()
                )
                .toList();

        bookGenreMapQueryJpaRepository.saveAllAndFlush(genreList);
//        List<Long> genreIdList = bookGenreMapQueryJpaRepository
//                .saveAll(genreList).stream()
//                .map((genreMap) -> genreMap.genreId)
//                .toList();

        return mapper.toDomain(savedEntity, domain.genreIdList);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookCommandJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
//        return memberJpaRepository.findAll(pageable) // returns Page<MemberEntity> ... -> Low Performance
//                .map(mapper::toDomain);
        return new PageImpl<>(
                bookCommandJpaRepository.findAllBy(pageable)
                        .stream()
                        .map(mapper::toDomain)
                        .toList(),
                pageable,
                bookCommandJpaRepository.count() // TODO use after refactor, ... if necessary
        );
    }
    
    @Override
    public boolean existsBookByTitle(String title) {
        return false;
    }

    @Override
    @Transactional
    public boolean update(String title, String description, BookStatus status, Long bookId) {
        BookEntity bookEntity = bookCommandJpaRepository.findById(bookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

        if (title != null || !"".equals(title)) {
            bookEntity.title = title;
        }

        if (description != null || !"".equals(description)) {
            bookEntity.description = description;
        }

        if (status != null) {
            bookEntity.status = status;
        }

        bookEntity.updatedAt = ServerTime.now();

        return true;
    }

    @Override
    public void deleteById(Long id) {
        bookCommandJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsNicknameByNickname(String nickname) {
        return bookCommandJpaRepository.existsNicknameByNickname(nickname);
    }
}
