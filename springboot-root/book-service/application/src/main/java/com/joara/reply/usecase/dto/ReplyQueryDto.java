package com.joara.reply.usecase.dto;

import com.joara.book.domain.model.reply.Reply;
import lombok.Builder;

import java.awt.print.Pageable;
import java.util.List;

public record ReplyQueryDto() {

    @Builder
    public record ReplyListResponseDto(
            List<Reply> replyList,
            Long lastPage
    ){}
}