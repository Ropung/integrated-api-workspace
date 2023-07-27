package com.joara.episode.repository;

import com.joara.book.domain.model.episode.type.EpisodeStatus;
import com.joara.episode.entity.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
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

    @Query("""
            UPDATE  EpisodeEntity epi
            SET     epi.status = :status,
                    epi.deletedAt =:deletedAt
            where   epi.id = :id
            """)
    @Modifying
    @Transactional
    Integer updateStatusAndDeletedAtById(UUID id, EpisodeStatus status, OffsetDateTime deletedAt);

    @Query("""
            UPDATE  EpisodeEntity epi
            SET     epi.status = :status
            where   epi.bookId = :bookId
                    and epi.status in :targetStatusList
            """)
    @Modifying
    @Transactional
    Integer updateAllStatusAndDeletedAtByIdAndInTargetStatusList(
            Long bookId,
            EpisodeStatus status,
            List<EpisodeStatus> targetStatusList
    );
}
