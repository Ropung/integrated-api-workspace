package com.joara.reply.repository;

import com.joara.reply.entitiy.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReplyQueryJapRepository extends JpaRepository<ReplyEntity, UUID> {
    Page<ReplyEntity> findAllByCommentId(UUID cid, Pageable pageable);

    boolean existsByCommentId(UUID commentId);
}
