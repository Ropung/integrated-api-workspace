package com.joara.episode.repository;

import com.joara.episode.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface EpisodeQueryJpaRepo extends JpaRepository<EpisodeEntity, UUID> {

    boolean existsById(UUID eid);
}
