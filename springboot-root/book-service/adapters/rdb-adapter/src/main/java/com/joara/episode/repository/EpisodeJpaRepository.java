package com.joara.episode.repository;

import com.joara.episode.entity.EpisodeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EpisodeJpaRepository extends JpaRepository<EpisodeEntity, UUID> {
    List<EpisodeEntity> findAllBy(Pageable pageable);
}
