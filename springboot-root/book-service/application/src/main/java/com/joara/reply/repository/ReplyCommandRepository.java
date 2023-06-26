package com.joara.reply.repository;

import com.joara.book.domain.model.reply.Reply;
import com.joara.support.repository.BaseCommandRepository;

import java.util.UUID;

public interface ReplyCommandRepository extends BaseCommandRepository<Reply, UUID> {
    boolean existsNicknameByNickname(String nickname);
    void update(UUID cid, UUID rid, String content);
    void delete(UUID rid);
}
