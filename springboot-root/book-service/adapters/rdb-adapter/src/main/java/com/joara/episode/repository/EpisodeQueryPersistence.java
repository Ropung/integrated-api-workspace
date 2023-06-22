package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EpisodeQueryPersistence implements EpisodeQueryRepository {

    private final EpisodeQueryJpaRepo episodeQueryJpaRepo;

    @Override
    public boolean existsById(UUID eid) {
        return episodeQueryJpaRepo.existsById(eid);
    }

    @Override
    public boolean existsByIdAndBookId(Long bid, UUID eid) {
        return episodeQueryJpaRepo.existsByBookIdAndId(bid, eid);
    }

    // Base
    @Override
    public Episode save(Episode domain) {
        return null;
    }

    // Base
    @Override
    public Optional<Episode> findById(UUID uuid) {
        return Optional.empty();
    }
}
