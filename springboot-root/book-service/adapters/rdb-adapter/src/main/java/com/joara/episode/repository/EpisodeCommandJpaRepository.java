package com.joara.episode.repository;


import com.joara.episode.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface EpisodeCommandJpaRepository extends JpaRepository<EpisodeEntity, UUID> {

//    List<EpisodeEntity> findAllBy(Pageable pageable);

}
