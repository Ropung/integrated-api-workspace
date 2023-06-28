package com.joara.favorite.repository;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.favorite.entity.MemberFavorBookEntity;
import com.joara.favorite.mapper.FavoriteEntityMapper;
import com.joara.favorite.respository.FavoriteCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public void deleteByMemberIdAndBookId(UUID memberId, Long bookId) {
        favoriteCommandJpaRepository.deleteByMemberIdAndBookId(memberId,bookId);
    }

    @Override
    public boolean existsByMemberIdAndBookId(UUID memberId, Long bookId) {
        return favoriteCommandJpaRepository.existsByMemberIdAndBookId(memberId,bookId);
    }

    @Override
    public Optional<MemberFavorBook> findById(UUID uuid) {
        return Optional.empty();
    }
}
