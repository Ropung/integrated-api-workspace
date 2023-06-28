package com.joara.favorite.repository;

import com.joara.favorite.entity.MemberFavorBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteCommandJpaRepository extends JpaRepository<MemberFavorBookEntity, UUID> {
    void deleteByMemberIdAndBookId(UUID memberId, Long bookId);

    boolean existsByMemberIdAndBookId(UUID memberId, Long bookId);
}
