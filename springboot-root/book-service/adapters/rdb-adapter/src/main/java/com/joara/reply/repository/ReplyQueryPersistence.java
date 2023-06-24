package com.joara.reply.repository;


import com.joara.book.domain.model.reply.Reply;
import com.joara.reply.entitiy.ReplyEntity;
import com.joara.reply.mapper.ReplyEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReplyQueryPersistence implements ReplyQueryRepository {

    private final ReplyQueryJapRepository replyQueryJapRepository;
    private final ReplyEntityMapper mapper;

    @Override
    public Page<Reply> findAllByCommentId(UUID cid, Pageable pageable) {
        Page<ReplyEntity> repliesEntities = replyQueryJapRepository
                .findAllByCommentId(cid, pageable);
        return repliesEntities.map(mapper::toDomainList);
    }

    @Override
    public Reply save(Reply domain) {
        return null;
    }

    @Override
    public Optional<Reply> findById(UUID uuid) {
        return Optional.empty();
    }
}