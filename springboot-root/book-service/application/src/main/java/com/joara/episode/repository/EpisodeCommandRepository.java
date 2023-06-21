package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.support.repository.BaseCommandRepository;

import java.util.UUID;

public interface EpisodeCommandRepository extends BaseCommandRepository<Episode, UUID> {
//    Page<Episode> findAll(Pageable pageable);  // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)
//
//    boolean existsEpisodeTitle(String title);
//    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044)
//
//    boolean update(String title, String description, BookStatus status, Long bookId);
//
//    void deleteById(Long id);
}
