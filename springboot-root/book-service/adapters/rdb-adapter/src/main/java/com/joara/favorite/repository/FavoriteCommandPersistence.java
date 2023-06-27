package com.joara.favorite.repository;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.favorite.entity.MemberFavorBookEntity;
import com.joara.favorite.mapper.FavoriteEntityMapper;
import com.joara.favorite.respository.FavoriteCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
@RequiredArgsConstructor
public class FavoriteCommandPersistence implements FavoriteCommandRepository {
    private final FavoriteEntityMapper mapper;
    private final FavoriteCommandJpaRepository favoriteCommandJpaRepository;
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
}
