package com.joara.comment.usecase;

import com.joara.book.domain.model.comment.Comment;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateRequestDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface CommentCreateUsecase {
    CommentCreateResponseDto create(Long bookId, UUID episodeId, CommentCreateRequestDto dto, HttpServletRequest request);
    boolean create(Long bookId, UUID episodeId, Comment comment, HttpServletRequest request);

}
