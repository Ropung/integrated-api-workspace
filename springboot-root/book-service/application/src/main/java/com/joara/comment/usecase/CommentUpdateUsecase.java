package com.joara.comment.usecase;

import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateRequestDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentUpdateResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface CommentUpdateUsecase {
    CommentUpdateResponseDto update(Long bookId, UUID episodeId, UUID commentId, CommentCreateRequestDto dto, HttpServletRequest request);
}