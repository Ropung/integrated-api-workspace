package com.joara.episode.repository;

import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.projection.EpisodeQueryProjections.EpisodeListViewProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public interface EpisodeQueryJpaRepo extends JpaRepository<EpisodeEntity, UUID> {

    boolean existsByBookIdAndId(Long bid, UUID eid);
    boolean existsById(UUID eid);
    Optional<EpisodeEntity> findByEpiNum(Long epiNum);
    Integer countByBookId(Long bookId);
    Optional<EpisodeEntity> findByBookIdAndEpiNum(Long bookId, Long epiNum);
    Page<EpisodeListViewProjection> findAllByBookIdAndStatusIn(Long bookId, List<EpisodeStatus> readableStatus, Pageable pageable);
}
