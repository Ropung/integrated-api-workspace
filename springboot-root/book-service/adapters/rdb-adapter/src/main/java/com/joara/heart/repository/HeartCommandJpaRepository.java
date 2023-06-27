package com.joara.heart.repository;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.heart.entity.EpisodeHeartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HeartCommandJpaRepository extends JpaRepository<EpisodeHeartEntity, UUID> {

    boolean existsByMemberIdAndEpiId(UUID memberId, UUID epiId);

    UUID findByMemberIdAndEpiId(UUID memberId, UUID epiId);

    void deleteByMemberIdAndEpiId(UUID memberId, UUID epiId);
}
