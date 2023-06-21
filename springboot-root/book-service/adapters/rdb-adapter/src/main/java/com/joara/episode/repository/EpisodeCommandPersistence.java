package com.joara.episode.repository;

import com.joara.book.domain.model.episode.Episode;
import com.joara.episode.entity.EpisodeEntity;
import com.joara.episode.mapper.EpisodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class EpisodeCommandPersistence implements EpisodeCommandRepository {

    private final EpisodeCommandJpaRepository episodeCommandJpaRepository;
    private final EpisodeEntityMapper mapper;

    @Override
    public Episode save(Episode domain) {
        EpisodeEntity entity = mapper.toEntity(domain);
        EpisodeEntity savedEntity = episodeCommandJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Episode> findById(UUID id) {
        return episodeCommandJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

//    @Override
//    public Page<Episode> findAll(Pageable pageable) {
////        return memberJpaRepository.findAll(pageable) // returns Page<MemberEntity> ... -> Low Performance
////                .map(mapper::toDomain);
//        return new PageImpl<>(
//                episodeCommandJpaRepository.findAllBy(pageable)
//                        .stream()
//                        .map(mapper::toDomain)
//                        .toList(),
//                pageable,
//                episodeCommandJpaRepository.count() // TODO use after refactor, ... if necessary
//        );
//    }



//    @Override
//    public boolean existsBookByTitle(String title) {
//        return false;
//    }

//    @Override
//    @Transactional
//    public boolean update(String title, String description, EpisodeStatus status, Long bookId) {
//        EpisodeEntity bookEntity = episodeCommandJpaRepository.findById(bookId)
//                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);
//
//        if (title != null && !"".equals(title)) {
//            bookEntity.title = title;
//        }
//
//        if (description != null && !"".equals(description)) {
//            bookEntity.description = description;
//        }
//
//        if (status != null) {
//            bookEntity.status = status;
//        }
//
//        return true;
//    }
//
//    @Override
//    public void deleteById(String title) {
//        episodeCommandJpaRepository.deleteByTitle(title);
//    }
}
