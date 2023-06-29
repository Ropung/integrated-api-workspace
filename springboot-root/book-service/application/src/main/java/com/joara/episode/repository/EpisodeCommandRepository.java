package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.support.repository.BaseCommandRepository;

import java.time.OffsetDateTime;
import java.util.UUID;

public interface EpisodeCommandRepository extends BaseCommandRepository<Episode, UUID> {
    void update(Long bid, UUID eid, String title, String content, String quote);
    boolean updateAllStatusByIdAndInTargetStatusList(
            Long bookId,
            EpisodeStatus status
    );
    boolean updateStatusAndDeletedAtByIdAndInTargetStatusList(
            UUID episodeId,
            EpisodeStatus status,
            OffsetDateTime deletedAt
    );
    void deleteById(UUID eid);
}
