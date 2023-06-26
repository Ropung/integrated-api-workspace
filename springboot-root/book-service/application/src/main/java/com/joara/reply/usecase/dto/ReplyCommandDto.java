package com.joara.reply.usecase.dto;

import lombok.Builder;

public record ReplyCommandDto() {

    public record ReplyCreateRequestDto(
            String content
    ){}

    public record ReplyUpdateRequestDto(
            String content
    ){}

    public record ReplyDeleteRequestDto(
            String content
    ){}


    @Builder
    public record ReplyCreateResponseDto(
            boolean success
    ){}

    @Builder
    public record ReplyUpdateResponseDto(
            boolean success
    ){}

    @Builder
    public record ReplyDeleteResponseDto(
            boolean success
    ){}
}
