package com.joara.episode.repository;

import com.joara.episode.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Component
public interface EpisodeCommandJpaRepo extends JpaRepository<EpisodeEntity, UUID> {

    /*
    1
    3 - 1
    4 - 1
    5 - 1
    6 - 1

    epi num > deleted num
     */
    @Query("""
            UPDATE  EpisodeEntity epi
            SET     epi.epiNum = epi.epiNum - 1
            where   epi.epiNum > :removedEpiNum
            """)
    @Modifying
    @Transactional
    Integer fixEpiNumAfterRemovedEpiNum(Long removedEpiNum);
}
