package com.joara.comment.repository;

import com.joara.book.domain.model.comment.Comment;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentQueryRepository extends BaseCommandRepository<Comment, UUID> {

    Page<Comment> findAllByEpiId(UUID episodeId, Pageable pageable);

    boolean existById(UUID cid);
}