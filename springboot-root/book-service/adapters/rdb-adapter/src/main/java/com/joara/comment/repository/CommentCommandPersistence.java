package com.joara.comment.repository;

import com.joara.book.domain.model.comment.Comment;
import com.joara.book.domain.model.comment.type.CommentStatus;
import com.joara.comment.entitiy.CommentEntity;
import com.joara.comment.exception.CommentErrorCode;
import com.joara.comment.mapper.CommentEntityMapper;
import com.joara.reply.exception.ReplyErrorCode;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CommentCommandPersistence implements CommentCommandRepository {

    private final CommentCommandJapRepository commentCommandJapRepository;
    private final CommentEntityMapper mapper;

    @Override
    public Comment save(Comment domain) {
        CommentEntity entity = mapper.toEntity(domain);
        CommentEntity saveEntity = commentCommandJapRepository.save(entity);
        return mapper.toDomain(saveEntity);
    }

    @Override
    public boolean existsNicknameByNickname(String nickname) {
        return commentCommandJapRepository.existsNicknameByNickname(nickname);
    }

    @Override
    @Transactional
    public void update(UUID episodeId, UUID commentId, String content) {
        CommentEntity commentEntity = commentCommandJapRepository.findById(commentId)
                .orElseThrow(ReplyErrorCode.REPLY_NOT_FOUND::defaultException);

        if (content == null || content.isEmpty()) throw CommentErrorCode.COMMENT_CONTENT_NOT_FOUND.defaultException();
        commentEntity.content = content;
        commentEntity.updatedAt = ServerTime.now();

        commentCommandJapRepository.save(commentEntity);
    }

    @Override
    public void updateStatus(UUID commentId) {
        CommentEntity commentEntity = commentCommandJapRepository.findById(commentId)
                .orElseThrow(ReplyErrorCode.REPLY_NOT_FOUND::defaultException);

//        if (content == null || content.isEmpty()) throw CommentErrorCode.COMMENT_CONTENT_NOT_FOUND.defaultException();
        commentEntity.status = CommentStatus.REMOVED;
        commentEntity.deletedAt = ServerTime.now();

        commentCommandJapRepository.save(commentEntity);
    }

    @Override
    @Transactional
    public void delete(UUID commentId) {
        CommentEntity commentEntity = commentCommandJapRepository.findById(commentId)
                .orElseThrow(CommentErrorCode.COMMENT_NOT_FOUND::defaultException);
        commentEntity.deletedAt = ServerTime.now();

        commentCommandJapRepository.deleteById(commentId);
    }

    @Override
    public Optional<Comment> findById(UUID uuid) {
        return Optional.empty();
    }
}
