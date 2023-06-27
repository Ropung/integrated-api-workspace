package com.joara.comment.usecase.dto;

import com.joara.book.domain.model.comment.Comment;
import lombok.Builder;

import java.util.List;

public record CommentQueryDto() {

    @Builder
    public record CommentListResponseDto(
            List<Comment> commentList,
            Long lastPage
    ){}
}