package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.support.repository.BaseCommandRepository;

import java.util.UUID;

public interface EpisodeCommandRepository extends BaseCommandRepository<Episode, UUID> {

    void update(Long bid, UUID eid, String title, String content, String quote);
    void deleteById(UUID eid);
}
