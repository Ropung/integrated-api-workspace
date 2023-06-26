package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.mapper.EpisodeEntityMapper;
import com.joara.episode.projection.EpisodeQueryProjections.EpisodeListViewProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EpisodeQueryPersistence implements EpisodeQueryRepository {

    private final EpisodeQueryJpaRepo episodeQueryJpaRepo;
    private final EpisodeEntityMapper mapper;

    @Override
    public boolean existsById(UUID eid) {
        return episodeQueryJpaRepo.existsById(eid);
    }

    @Override
    public boolean existsByIdAndBookId(Long bid, UUID eid) {
        return episodeQueryJpaRepo.existsByBookIdAndId(bid, eid);
    }

    @Override
    public Page<EpisodeListViewReadModel> findAllByBookId(Long bookId, Pageable pageable) {
        Page<EpisodeListViewProjection> episodeEntities = episodeQueryJpaRepo
                .findAllByBookId(bookId, pageable);
        return episodeEntities.map(mapper::toReadModel);
    }
    @Override
    public Optional<Episode> findById(UUID uuid) {
        Optional<EpisodeEntity> episodeEntity = episodeQueryJpaRepo.findById(uuid);
        return episodeEntity.map(mapper::toDomain);
    }

    // Base
    @Override
    public Episode save(Episode domain) {
        return null;
    }

}
