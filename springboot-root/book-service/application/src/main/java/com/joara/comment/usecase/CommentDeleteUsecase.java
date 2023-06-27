package com.joara.comment.usecase;

import com.joara.comment.usecase.dto.CommentCommandDto.CommentDeleteResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface CommentDeleteUsecase {

    CommentDeleteResponseDto delete(Long bookId, UUID episodeId, UUID commentId, HttpServletRequest request);
}
