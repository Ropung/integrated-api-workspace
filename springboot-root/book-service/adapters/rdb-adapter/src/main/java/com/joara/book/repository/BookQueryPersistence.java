package com.joara.book.repository;

import com.joara.book.domain.model.BookReadModels.BookDetailedViewReadModel;
import com.joara.book.domain.model.BookReadModels.BookListViewReadModel;
import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.type.BookStatus;
import com.joara.book.mapper.BookEntityMapper;
import com.joara.book.projection.BookQueryProjections.BookDetailedViewProjection;
import com.joara.book.projection.BookQueryProjections.BookListViewProjection;
import com.joara.book.projection.BookQueryProjections.BookTitleProjection;
import com.joara.episode.repository.EpisodeQueryJpaRepo;
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
    private final EpisodeQueryJpaRepo episodeQueryJpaRepo;
    private final GenreQueryJpaRepository genreQueryJpaRepository;
    private final BookEntityMapper mapper;

    @Override
    public Optional<String> findTitleById(Long bookId) {
        return bookQueryJpaRepository.findBookEntityById(bookId)
                .map(BookTitleProjection::title);
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

        Integer episodeSize = episodeQueryJpaRepo.countByBookId(bookId);

        return optionalBook.map((book) -> {
            BookGenreMappedInfo genreInfo = findBookGenreMapByBookId(book.id());

            return mapper.toReadModel(
                    book,
                    episodeSize,
                    genreInfo.genreIds,
                    genreInfo.genreNames
            );
        });
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndTitleContainsIgnoreCaseAndStatusIn(
            Long genreId,
            String keyword,
            List<BookStatus> readableStatus,
            Pageable pageable
    ) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByTitleContainsIgnoreCaseAndStatusIn(keyword, readableStatus, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndDescriptionContainsIgnoreCaseAndStatusIn(
            Long genreId,
            String keyword,
            List<BookStatus> readableStatus,
            Pageable pageable
    ) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByDescriptionContainsIgnoreCaseAndStatusIn(keyword, readableStatus, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndNicknameContainsIgnoreCaseAndStatusIn(
            Long genreId,
            String keyword,
            List<BookStatus> readableStatus,
            Pageable pageable
    ) {
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByNicknameContainsIgnoreCaseAndStatusIn(keyword, readableStatus, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findAllByGenreIdAndStatusIn(
            Long genreId,
            List<BookStatus> readableStatus,
            Pageable pageable
    ) {
        // book genre map -> by genre id -> book list
        List<Long> bookIdList = bookGenreMapQueryJpaRepository
                .findByGenreId(genreId).stream()
                .map((item) -> item.bookId)
                .toList();
        Page<BookListViewProjection> bookEntities = bookQueryJpaRepository
                .findAllByIdInAndStatusIn(bookIdList, readableStatus, pageable);

        return bookEntities.map(this::mapToBookListViewModel);
    }

    @Override
    public Page<BookListViewReadModel> findBooksByMemberId(UUID memberId, Pageable pageable) {
        return bookQueryJpaRepository
                .findBooksByMemberId(memberId, pageable)
                .map(this::mapToBookListViewModel); // for -- 하나하나 변환
    }

    @Override
    public Optional<BookListViewReadModel> findListViewItemById(Long bookId) {
        return bookQueryJpaRepository.findListViewItemById(bookId)
                .map(this::mapToBookListViewModel);
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

    private BookDetailedViewReadModel mapToBookDetailedListViewModel(BookDetailedViewProjection projection) {
        Long bookId = projection.id();

        // TODO refactor: 지금은 작품마다 매번 장르 DB 조회 -> 미리 필요한 장르 목록 다 조회해 놓고 사용.
        BookGenreMappedInfo genreInfo = findBookGenreMapByBookId(bookId);
        Integer episodeSize = episodeQueryJpaRepo.countByBookId(bookId);

        return mapper.toReadModel(
                projection,
                episodeSize,
                genreInfo.genreIds,
                genreInfo.genreNames
        );
    }

    private BookListViewReadModel mapToBookListViewModel(BookListViewProjection projection) {
        Long bookId = projection.id();

        // TODO refactor: 지금은 작품마다 매번 장르 DB 조회 -> 미리 필요한 장르 목록 다 조회해 놓고 사용.
        BookGenreMappedInfo genreInfo = findBookGenreMapByBookId(bookId);
        Integer episodeSize = episodeQueryJpaRepo.countByBookId(bookId);

        return mapper.toReadModel(
                projection,
                episodeSize,
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

