package com.joara.reply.repository;

import com.joara.book.domain.model.reply.Reply;
import com.joara.reply.entitiy.ReplyEntity;
import com.joara.reply.exception.ReplyErrorCode;
import com.joara.reply.mapper.ReplyEntityMapper;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ReplyCommandPersistence implements ReplyCommandRepository {

    private final ReplyCommandJapRepository replyCommandJapRepository;
    private final ReplyEntityMapper mapper;

    @Override
    public Reply save(Reply domain) {
        ReplyEntity entity = mapper.toEntity(domain);
        ReplyEntity saveEntity = replyCommandJapRepository.save(entity);
        return mapper.toDomain(saveEntity);
    }

    @Override
    public boolean existsNicknameByNickname(String nickname) {
        return replyCommandJapRepository.existsNicknameByNickname(nickname);
    }

    @Override
    @Transactional
    public void update(UUID cid, UUID rid, String content) {
        ReplyEntity replyEntity = replyCommandJapRepository.findById(rid)
                .orElseThrow(ReplyErrorCode.REPLY_NOT_FOUND::defaultException);

        if (content == null || content.isEmpty()) throw ReplyErrorCode.REPLY_CONTENT_NOT_FOUND.defaultException();
        replyEntity.content = content;
        replyEntity.updatedAt = ServerTime.now();

        replyCommandJapRepository.save(replyEntity);
    }

    @Override
    @Transactional
    public void delete(UUID rid) {
        ReplyEntity replyEntity = replyCommandJapRepository.findById(rid)
                .orElseThrow(ReplyErrorCode.REPLY_NOT_FOUND::defaultException);
        replyEntity.deletedAt = ServerTime.now();

        replyCommandJapRepository.deleteById(rid);
    }

    @Override
    public Optional<Reply> findById(UUID uuid) {
        return Optional.empty();
    }
}
