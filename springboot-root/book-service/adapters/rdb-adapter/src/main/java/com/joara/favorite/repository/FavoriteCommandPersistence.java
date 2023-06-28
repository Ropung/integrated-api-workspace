package com.joara.favorite.repository;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.book.entity.BookGenreMapEntity;
import com.joara.book.repository.BookGenreMapQueryJpaRepository;
import com.joara.favorite.entity.MemberFavorBookEntity;
import com.joara.favorite.mapper.FavoriteEntityMapper;
import com.joara.favorite.respository.FavoriteCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
@RequiredArgsConstructor
public class FavoriteCommandPersistence implements FavoriteCommandRepository {
    private final FavoriteEntityMapper mapper;
    private final FavoriteCommandJpaRepository favoriteCommandJpaRepository;
    private final BookGenreMapQueryJpaRepository bookGenreMapQueryJpaRepository;
    @Override
    public MemberFavorBook save(MemberFavorBook domain) {

        MemberFavorBookEntity entity = mapper.toEntity(domain);
        MemberFavorBookEntity savedEntity = favoriteCommandJpaRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }


    @Override
    public Optional<MemberFavorBook> findById(UUID uuid) {
        return Optional.empty();
    }
}
