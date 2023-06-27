package com.joara.book.repository;

import com.joara.book.domain.model.BookReadModels.AnalyzedBookReadModel;
import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.entity.BookEntity;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.book.projection.BookQueryProjections.AnalyzedBookProjection;
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
import java.util.UUID;

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
    public Optional<BookDetailedViewReadModel> findDetailedViewById(Long bookId) {
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
                .findAllByTitleContainsIgnoreCase(keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndDescriptionContainsIgnoreCase(Long genreId, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByDescriptionContainsIgnoreCase(keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndNicknameContainsIgnoreCase(Long genreId, String keyword, Pageable pageable) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByNicknameContainsIgnoreCase(keyword, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreId(Long genreId, Pageable pageable) {
        // book genre map -> by genre id -> book list
        List<Long> bookIdList = bookGenreMapQueryJpaRepository
                .findByGenreId(genreId).stream()
                .map((item) -> item.bookId)
                .toList();
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByIdIn(bookIdList, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public AnalyzedBookReadModel findAnalyzedBookById(Long bookId) {
        List<Long> bookIdList = bookGenreMapQueryJpaRepository
                .findByBookId(bookId).stream()
                .map((genre) -> genre.genreId)
                .toList();
//        List<Long> bookIdList = bookGenreMapQueryJpaRepository
//                .findByBookId(bookId).stream()
//                .map((item) -> item.bookId)
//                .toList();
        List<GenreEntity> genreResultSet = genreQueryJpaRepository.findAllByIdIn(bookIdList);

        List<String> genreNames = genreResultSet.stream()
                .map((genre) -> genre.kor)
                .toList();

        AnalyzedBookProjection bookEntities = bookQueryJpaRepository
                .findAnalyzedBookById(bookId);

        return AnalyzedBookReadModel.builder()
                .id(bookId)
                .genreIdList(bookIdList)
                .genreNameList(genreNames)
                .title(bookEntities.title())
                .coverUrl(bookEntities.coverUrl())
                .build();
    }

    @Override
    public Page<BookListViewReadModel> findBooksByMemberId(UUID memberId, Pageable pageable) {
        return bookQueryJpaRepository
                .findBooksByMemberId(memberId, pageable)
                .map(this::mapToBookListViewModel); // for -- 하나하나 변환
    }

    private BookGenreMappedInfo findBookGenreMapByBookId(Long bookId) {
        List<Long> genreIds = bookGenreMapQueryJpaRepository.findByBookId(bookId).stream()
                .map((genre) -> genre.genreId)
                .toList();
        List<GenreEntity> genreResultSet = genreQueryJpaRepository.findAllByIdIn(genreIds);

        List<String> genreNames = genreResultSet.stream()
                .map((genre) -> genre.kor)
                .toList();

        return BookGenreMappedInfo.builder()
                .genreIds(genreIds)
                .genreNames(genreNames)
                .build();
    }

    /**
     * 리스트뷰 프로젝션 넣으면 장르 등을 포함해서 리스트뷰 리드모델로 변환
     * @param projection
     * @return
     */

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

