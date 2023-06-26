package com.joara.heart.repository;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.heart.entity.EpisodeHeartEntity;

import com.joara.heart.mapper.EpisodeHeartEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HeartCommandPersistence implements HeartCommandRepository{
    private final HeartCommandJpaRepository heartCommandJpaRepository;
    private final EpisodeHeartEntityMapper mapper;

    @Override
    public void deleteByMemberId(UUID memberId) {
        heartCommandJpaRepository.deleteByMemberId(memberId);
    }

    @Override
    public EpisodeHeart save(EpisodeHeart domain) {
        EpisodeHeartEntity entity = mapper.toEntity(domain);
        EpisodeHeartEntity saveEntity = heartCommandJpaRepository.save(entity);
        return mapper.toDomain(saveEntity);
    }

    @Override
    public Optional<EpisodeHeart> findById(UUID uuid) {
        return Optional.empty();
    }


}
