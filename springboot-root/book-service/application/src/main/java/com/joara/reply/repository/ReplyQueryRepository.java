package com.joara.reply.repository;

import com.joara.book.domain.model.reply.Reply;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReplyQueryRepository extends BaseCommandRepository<Reply, UUID> {

    Page<Reply> findAllByCommentId(UUID cid, Pageable pageable);
}