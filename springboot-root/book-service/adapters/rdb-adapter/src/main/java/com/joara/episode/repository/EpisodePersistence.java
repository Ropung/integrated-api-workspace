package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.book.repository.EpisodeRepository;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.mapper.EpisodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EpisodePersistence implements EpisodeRepository {
    // Delegation
     private final EpisodeJpaRepository episodeJpaRepository;
     private final EpisodeEntityMapper mapper;

    @Override
    public Episode save(Episode domain) {
        // use mapper: Member -> MemberEntity
        // use mapper: MemberEntity -> Member
        EpisodeEntity entity = mapper.toEntity(domain);
        EpisodeEntity savedEntity = episodeJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Episode> findById(UUID uuid) {
        return episodeJpaRepository.findById(uuid)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Episode> findAll(Pageable pageable) {
//        return memberJpaRepository.findAll(pageable) // returns Page<MemberEntity> ... -> Low Performance
//                .map(mapper::toDomain);
        return new PageImpl<>(
                episodeJpaRepository.findAllBy(pageable)
                        .stream()
                        .map(mapper::toDomain)
                        .toList(),
                pageable,
                episodeJpaRepository.count() // TODO use after refactor, ... if necessary
        );
    }
}
