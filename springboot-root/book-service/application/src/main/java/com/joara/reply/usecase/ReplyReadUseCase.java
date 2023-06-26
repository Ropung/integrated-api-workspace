package com.joara.reply.usecase;

import com.joara.reply.usecase.dto.ReplyQueryDto.ReplyListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReplyReadUseCase{
    ReplyListResponseDto findAllByCommentId(UUID cid, Pageable pageable);
}