package com.joara.book.repository;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.entity.BookGenreMapEntity;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.genre.entity.GenreEntity;
import com.joara.genre.repository.GenreQueryJpaRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookQueryPersistence implements BookQueryRepository {
    private final BookQueryJpaRepository bookQueryJpaRepository;
    private final BookGenreMapQueryJpaRepository bookGenreMapQueryJpaRepository;
    private final GenreQueryJpaRepository genreQueryJpaRepository;
    private final BookEntityMapper mapper;

    @Override
    public String findTitleByBookId(Long bookId) {
        BookEntity bookEntity = bookQueryJpaRepository.findBookEntityById(bookId);
        if (bookEntity != null) {
            return bookEntity.title;
        }
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return bookQueryJpaRepository.existsById(id);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        // TODO
        return null;
    }

    @Override
    public Book save(Book domain) {
        // TODO
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookQueryJpaRepository
                .findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<BookDetailedViewReadModel> findDetailedViewByBookId(Long bookId) {
        Optional<BookDetailedViewProjection> optionalBook =
                bookQueryJpaRepository.findDetailedProjectionById(bookId);

        return optionalBook.map((book) -> {
            BookGenreMappedInfo genreInfo = findBookGenreMapByBookId(book.id());

            return mapper.toReadModel(
                    book,
                    genreInfo.genreIds,
                    genreInfo.genreNames
            );
        });
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndTitleContainsIgnoreCase(Long genreId, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndTitleContainsIgnoreCase(genreId, keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndDescriptionContainsIgnoreCase(Long genreId, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndDescriptionContainsIgnoreCase(genreId, keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndNicknameContainsIgnoreCase(Long genreId, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreIdAndNicknameContainsIgnoreCase(genreId, keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreId(Long genreId, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByGenreId(genreId, pageable);
        return bookEntities.map(this::mapToBookListViewModel);
    }

    private BookGenreMappedInfo findBookGenreMapByBookId(Long bookId) {
        List<BookGenreMapEntity> genreIdResultSet = bookGenreMapQueryJpaRepository.findByBookId(bookId);
        List<GenreEntity> genreResultSet = genreQueryJpaRepository.findAllInGenreId(genreIdResultSet);

        List<Long> genreIds = genreIdResultSet.stream()
                .map((genre) -> genre.genreId)
                .toList();
        List<String> genreNames = genreResultSet.stream()
                .map((genre) -> genre.kor)
                .toList();

        return BookGenreMappedInfo.builder()
                .genreIds(genreIds)
                .genreNames(genreNames)
                .build();
    }

    private BookListViewReadModel mapToBookListViewModel(BookListViewProjection projection) {

        // TODO refactor: 지금은 작품마다 매번 장르 DB 조회 -> 미리 필요한 장르 목록 다 조회해 놓고 사용.
        BookGenreMappedInfo genreInfo = findBookGenreMapByBookId(projection.id());

        return mapper.toReadModel(
                projection,
                genreInfo.genreIds,
                genreInfo.genreNames
        );
    }

    @Builder
    private record BookGenreMappedInfo(
            List<Long> genreIds,
            List<String> genreNames
    ) {}
}
