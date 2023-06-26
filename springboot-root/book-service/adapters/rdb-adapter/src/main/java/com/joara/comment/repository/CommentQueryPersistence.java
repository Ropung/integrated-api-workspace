package com.joara.comment.repository;


import com.joara.book.domain.model.comment.Comment;
import com.joara.comment.entitiy.CommentEntity;
import com.joara.comment.mapper.CommentEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentQueryPersistence implements CommentQueryRepository {

    private final CommentQueryJapRepository commentQueryJapRepository;
    private final CommentEntityMapper mapper;

    @Override
    public Page<Comment> findAllByEpiId(UUID episodeId, Pageable pageable) {
        Page<CommentEntity> commentsEntities = commentQueryJapRepository
                .findAllByEpiId(episodeId, pageable);
        return commentsEntities.map(mapper::toDomainList);
    }

    @Override
    public boolean existById(UUID cid) {
        return commentQueryJapRepository.existsById(cid);
    }

    @Override
    public Comment save(Comment domain) {
        return null;
    }

    @Override
    public Optional<Comment> findById(UUID uuid) {
        return Optional.empty();
    }
}