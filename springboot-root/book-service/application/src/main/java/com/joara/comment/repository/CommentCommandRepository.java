package com.joara.comment.repository;

import com.joara.book.domain.model.comment.Comment;
import com.joara.support.repository.BaseCommandRepository;

import java.util.UUID;

public interface CommentCommandRepository extends BaseCommandRepository<Comment, UUID> {
    boolean existsNicknameByNickname(String nickname);
    void update(UUID episodeId, UUID commentId, String content);
    void delete(UUID commentId);
    void updateStatus(UUID commentId);
}
