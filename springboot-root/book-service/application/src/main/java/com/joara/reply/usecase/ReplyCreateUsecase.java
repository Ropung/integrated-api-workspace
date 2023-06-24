package com.joara.reply.usecase;

import com.joara.book.domain.model.reply.Reply;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateRequestDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface ReplyCreateUsecase {
    ReplyCreateResponseDto create(Long bid, UUID eid, UUID cid, ReplyCreateRequestDto dto, HttpServletRequest request);
    boolean create(Long bid, UUID eid, UUID cid, Reply reply, HttpServletRequest request);

}
