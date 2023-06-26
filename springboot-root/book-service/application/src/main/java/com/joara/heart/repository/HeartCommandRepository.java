package com.joara.heart.repository;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.support.repository.BaseCommandRepository;

import java.util.Optional;
import java.util.UUID;

public interface HeartCommandRepository extends BaseCommandRepository<EpisodeHeart, UUID> {
    void deleteByMemberId(UUID memberId);
}
