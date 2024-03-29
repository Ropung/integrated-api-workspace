package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.exception.EpisodeErrorCode;
import com.joara.episode.mapper.EpisodeEntityMapper;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EpisodeCommandPersistence implements EpisodeCommandRepository {

    private final EpisodeCommandJpaRepo episodeCommandJpaRepo;
    private final EpisodeQueryJpaRepo episodeQueryJpaRepo;
    private final EpisodeEntityMapper mapper;

    @Override
    public Episode save(Episode domain) {
        int episodeSize = episodeQueryJpaRepo.countByBookId(domain.bookId);
        domain.epiNum = (long) (episodeSize + 1);

        EpisodeEntity entity = mapper.toEntity(domain);
        EpisodeEntity savedEntity = episodeCommandJpaRepo.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Episode> findById(UUID id) {
        return episodeCommandJpaRepo.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public void update(Long bid, UUID eid, String title, String content, String quote) {

        boolean isBook = episodeQueryJpaRepo.existsByBookIdAndId(bid, eid);

        if (!isBook) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

        EpisodeEntity episodeEntity = episodeCommandJpaRepo.findById(eid)
                .orElseThrow(BookErrorCode.EPISODE_NOT_FOUND::defaultException);

        if (title == null && title.isEmpty()) throw BookErrorCode.EPISODE_TITLE_NOT_FOUND.defaultException();
            episodeEntity.epiTitle = title;

        if (content == null && content.isEmpty()) throw BookErrorCode.EPISODE_CONTENT_NOT_FOUND.defaultException();
            episodeEntity.content = content;

        // 작가의 한마디는 null 가능
        episodeEntity.quote = quote;
        episodeEntity.updatedAt = ServerTime.now();

        episodeCommandJpaRepo.save(episodeEntity);
    }

    @Override
    public boolean updateAllStatusByIdAndInTargetStatusList(Long bookId, EpisodeStatus status) {
        return 0 < episodeCommandJpaRepo
                .updateAllStatusAndDeletedAtByIdAndInTargetStatusList(
                        bookId,
                        status,
                        List.of(EpisodeStatus.ACTIVE)
                );
    }

    @Override
    public boolean updateStatusAndDeletedAtByIdAndInTargetStatusList(
            UUID episodeId,
            EpisodeStatus status,
            OffsetDateTime deletedAt
    ) {
        return 0 < episodeCommandJpaRepo
                .updateStatusAndDeletedAtById(
                        episodeId,
                        status,
                        deletedAt
                );
    }

    @Override
    public void deleteById(UUID eid) {
        EpisodeEntity episode = episodeCommandJpaRepo.findById(eid)
                .orElseThrow(EpisodeErrorCode.EPISODE_ID_NOT_FOUND::defaultException);
        episodeCommandJpaRepo.deleteById(eid);
        episodeCommandJpaRepo.fixEpiNumAfterRemovedEpiNum(episode.epiNum);
    }
}
