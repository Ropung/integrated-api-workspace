package com.joara.favorite.repository;

import com.joara.favorite.entity.MemberFavorBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FavoriteQueryJpaRepository extends JpaRepository<MemberFavorBookEntity, UUID> {
    Page<MemberFavorBookEntity> findByMemberId(UUID memberId, Pageable pageable);
}
