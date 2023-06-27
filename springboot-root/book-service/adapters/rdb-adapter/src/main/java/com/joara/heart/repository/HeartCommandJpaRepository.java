package com.joara.heart.repository;

import com.joara.heart.entity.EpisodeHeartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeartCommandJpaRepository extends JpaRepository<EpisodeHeartEntity, UUID> {
    void deleteByMemberId(UUID memberId);

    boolean existsByMemberIdAndEpiId(UUID memberId, UUID epiId);
}
