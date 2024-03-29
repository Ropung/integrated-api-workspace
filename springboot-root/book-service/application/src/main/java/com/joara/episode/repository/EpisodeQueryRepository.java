package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.episode.domain.model.EpisodeReadModel.EpisodeListViewReadModel;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EpisodeQueryRepository extends BaseCommandRepository<Episode, UUID> {

    boolean existsById(UUID eid);

    boolean existsByIdAndBookId(Long bid, UUID eid);

    Page<EpisodeListViewReadModel> findAllByBookIdAndStatusInReadableStatus(Long bookId, List<EpisodeStatus> readableStatus, Pageable pageable);

    // 단건 조회
    Optional<Episode> findByBookIdAndEpiNum(Long bookId, Long epiNum);
}