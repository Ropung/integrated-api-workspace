package com.joara.favorite.repository;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.book.entity.BookGenreMapEntity;
import com.joara.book.repository.BookGenreMapQueryJpaRepository;
import com.joara.favorite.entity.MemberFavorBookEntity;
import com.joara.favorite.mapper.FavoriteEntityMapper;
import com.joara.favorite.respository.FavoriteCommandRepository;
import com.joara.favorite.respository.FavoriteQueryRepository;
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
public class FavoriteQueryPersistence implements FavoriteQueryRepository, FavoriteCommandRepository {

    private final FavoriteQueryJpaRepository favoriteQueryJpaRepository;
    private final FavoriteCommandJpaRepository favoriteCommandJpaRepository;
    private final BookGenreMapQueryJpaRepository bookGenreMapQueryJpaRepository;
    private final GenreQueryJpaRepository genreQueryJpaRepository;
    private final FavoriteEntityMapper mapper;


    @Override
    public MemberFavorBook save(MemberFavorBook domain) {
        MemberFavorBookEntity entity = mapper.toEntity(domain);
        MemberFavorBookEntity savedEntity = favoriteCommandJpaRepository.save(entity);
//        List<BookGenreMapEntity> genreList = domain.genreIdList.stream()
//                .map((genreId) -> BookGenreMapEntity.builder()
//                        .bookId(savedEntity.getId())
//                        .genreId(genreId)
//                        .build()
//                ).toList();
//        bookGenreMapQueryJpaRepository.saveAllAndFlush(genreList);

//        List<Long> genreIdList = bookGenreMapQueryJpaRepository
//                .saveAll(genreList).stream()
//                .map((genreMap) -> genreMap.genreId)
//                .toList();

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<MemberFavorBook> findById(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public Page<MemberFavorBook> findByMemberId(UUID memberId, Pageable pageable) {
        return favoriteQueryJpaRepository.findByMemberId(memberId, pageable)
                .map(this::mapToMemberFavorBook);
    }

    private FavoriteGenreMappedInfo findBookGenreMapByBookId(Long bookId) {
        List<Long> genreIds = bookGenreMapQueryJpaRepository.findByBookId(bookId).stream()
                .map((genre) -> genre.genreId)
                .toList();
        List<GenreEntity> genreResultSet = genreQueryJpaRepository.findAllByIdIn(genreIds);

        List<String> genreNames = genreResultSet.stream()
                .map((genre) -> genre.kor)
                .toList();

        return FavoriteGenreMappedInfo.builder()
                .genreIds(genreIds)
                .genreNames(genreNames)
                .build();
    }

    private MemberFavorBook mapToMemberFavorBook(MemberFavorBookEntity entity) {
        FavoriteGenreMappedInfo genreInfo = findBookGenreMapByBookId(entity.bookId);

        return mapper.toReadModel(
                entity,
                genreInfo.genreIds,
                genreInfo.genreNames

        );

    }
    @Builder
    private record FavoriteGenreMappedInfo(
            List<Long> genreIds,
            List<String> genreNames

    ) {}
}
