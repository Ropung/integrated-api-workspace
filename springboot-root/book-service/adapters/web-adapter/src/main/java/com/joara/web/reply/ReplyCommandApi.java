package com.joara.web.reply;

import com.joara.reply.usecase.ReplyCreateUsecase;
import com.joara.reply.usecase.ReplyDeleteUsecase;
import com.joara.reply.usecase.ReplyUpdateUsecase;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateRequestDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateResponseDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyDeleteResponseDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bid}/episode/{eid}/comment/{cid}/reply")
public class ReplyCommandApi {

    private final ReplyCreateUsecase replyCreateUsecase;
    private final ReplyUpdateUsecase replyUpdateUsecase;
    private final ReplyDeleteUsecase replyDeleteUsecase;

    @PostMapping()
    public ReplyCreateResponseDto create(
            @PathVariable Long bid, @PathVariable UUID eid, @PathVariable UUID cid,
            @RequestBody @Valid ReplyCreateRequestDto dto,
            HttpServletRequest request
    ){
        return replyCreateUsecase.create(bid, eid, cid, dto, request);
    }

    @PutMapping("/{rid}")
    public ReplyUpdateResponseDto update(
            @PathVariable Long bid, @PathVariable UUID eid, @PathVariable UUID cid, @PathVariable UUID rid,
            @RequestBody @Valid ReplyCreateRequestDto dto,
            HttpServletRequest request
    ){

        return replyUpdateUsecase.update(bid, eid, cid, rid, dto, request);
    }

    @DeleteMapping("/{rid}")
    public ReplyDeleteResponseDto delete(
            @PathVariable Long bid, @PathVariable UUID eid, @PathVariable UUID cid, @PathVariable UUID rid,
            @RequestBody @Valid ReplyCreateRequestDto dto,
            HttpServletRequest request
    ){

        return replyDeleteUsecase.delete(bid, eid, cid, rid, dto, request);
    }
}
