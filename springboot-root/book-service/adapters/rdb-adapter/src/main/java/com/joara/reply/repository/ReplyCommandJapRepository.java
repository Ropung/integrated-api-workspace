package com.joara.reply.repository;

import com.joara.reply.entitiy.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReplyCommandJapRepository extends JpaRepository<ReplyEntity, UUID> {
    boolean existsNicknameByNickname(String nickname);
}
