package com.joara.comment.repository;

import com.joara.comment.entitiy.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentQueryJapRepository extends JpaRepository<CommentEntity, UUID> {
    Page<CommentEntity> findAllByEpiId(UUID episodeId, Pageable pageable);
}
