package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.exception.BookErrorCode;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.mapper.EpisodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public boolean update(Long bid, UUID eid, String title, String content, String quote) {
        boolean isBook = episodeQueryJpaRepo.existsByBookIdAndId(bid, eid);
        if (!isBook) {
            throw BookErrorCode.BOOK_NOT_FOUND.defaultException();
        }
        EpisodeEntity episodeEntity = episodeCommandJpaRepo.findById(eid)
                .orElseThrow(BookErrorCode.EPISODE_NOT_FOUND::defaultException);

        if (title != null && !title.isEmpty()) {
            episodeEntity.epiTitle = title;
        }

        if (content != null && !content.isEmpty()) {
            episodeEntity.content = content;
        }

        if (quote != null && !quote.isEmpty()) {
            episodeEntity.quote = quote;
        }

        episodeCommandJpaRepo.save(episodeEntity);
        return true;
    }

    @Override
    public void deleteById(UUID eid) {
        episodeCommandJpaRepo.deleteById(eid);
    }
}
