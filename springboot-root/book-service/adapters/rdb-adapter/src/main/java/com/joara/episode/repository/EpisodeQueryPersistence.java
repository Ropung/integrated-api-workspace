package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.entity.EpisodeEntity;
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
