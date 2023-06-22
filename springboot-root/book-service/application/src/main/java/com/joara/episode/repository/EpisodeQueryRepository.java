package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.support.repository.BaseCommandRepository;

import java.util.Optional;
import java.util.UUID;

public interface EpisodeQueryRepository extends BaseCommandRepository<Episode, UUID> {

    boolean existsById(UUID eid);

    boolean existsByIdAndBookId(Long bid, UUID eid);
}
