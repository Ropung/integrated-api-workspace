package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.mapper.EpisodeEntityMapper;
import com.joara.episode.projection.EpisodeQueryProjections.EpisodeListViewProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Optional<Episode> findByBookIdAndEpiNum(Long bookId, Long epiNum) {
        Optional<EpisodeEntity> episodeEntity = episodeQueryJpaRepo.findByBookIdAndEpiNum(bookId, epiNum);
        return episodeEntity.map(mapper::toDomain);
    }


    @Override
    public boolean existsByIdAndBookId(Long bid, UUID eid) {
        return episodeQueryJpaRepo.existsByBookIdAndId(bid, eid);
    }

    @Override
    public Page<EpisodeListViewReadModel> findAllByBookIdAndStatusInReadableStatus(Long bookId, List<EpisodeStatus> readableStatus, Pageable pageable) {
        Page<EpisodeListViewProjection> episodeEntities = episodeQueryJpaRepo
                .findAllByBookIdAndStatusIn(bookId, readableStatus, pageable);
        return episodeEntities.map(mapper::toReadModel);
    }



    // Base
    @Override
    public Episode save(Episode domain) {
        return null;
    }

    @Override
    public Optional<Episode> findById(UUID uuid) {
        return Optional.empty();
    }


}
