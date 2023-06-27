package com.joara.comment.repository;

import com.joara.comment.entitiy.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentCommandJapRepository extends JpaRepository<CommentEntity, UUID> {
    boolean existsNicknameByNickname(String nickname);
}
