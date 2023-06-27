package com.joara.comment.usecase;

import com.joara.comment.usecase.dto.CommentQueryDto.CommentListResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CommentReadUseCase {
    CommentListResponseDto findAllByEpiId(UUID episodeId, Pageable pageable);
}