package com.joara.web.reply;

import com.joara.reply.usecase.ReplyReadUseCase;
import com.joara.reply.usecase.dto.ReplyQueryDto.ReplyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bid}/episode/{eid}/comment/{cid}/reply")
public class ReplyQueryApi {

    private final ReplyReadUseCase replyReadUseCase;

    @GetMapping()
    public ReplyListResponseDto replyListResponseDto(
            @PathVariable UUID cid,
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst();
        return replyReadUseCase.findAllByCommentId(cid, pageable);
    }
}