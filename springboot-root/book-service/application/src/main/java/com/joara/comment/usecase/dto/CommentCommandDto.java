package com.joara.comment.usecase.dto;

import lombok.Builder;

public record CommentCommandDto() {

    public record CommentCreateRequestDto(
            String content
    ){}

    public record CommentUpdateRequestDto(
            String content
    ){}

    @Builder
    public record CommentCreateResponseDto(
            boolean success
    ){}

    @Builder
    public record CommentUpdateResponseDto(
            boolean success
    ){}

    @Builder
    public record CommentDeleteResponseDto(
            boolean success
    ){}
}
