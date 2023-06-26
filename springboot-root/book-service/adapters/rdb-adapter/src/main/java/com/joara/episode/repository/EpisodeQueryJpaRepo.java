package com.joara.episode.repository;

import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.projection.EpisodeQueryProjections.EpisodeListViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public interface EpisodeQueryJpaRepo extends JpaRepository<EpisodeEntity, UUID> {

    boolean existsByBookIdAndId(Long bid, UUID eid);

    boolean existsById(UUID eid);

    Page<EpisodeListViewProjection> findAllByBookId(Long bookId, Pageable pageable);

    Optional<EpisodeEntity> findById(UUID eid);
}
