package com.joara.web.comment;

import com.joara.comment.usecase.CommentReadUseCase;
import com.joara.comment.usecase.dto.CommentQueryDto.CommentListResponseDto;
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
@RequestMapping("/books/{bookId}/episode/{episodeId}/comment")
public class CommentQueryApi {

    private final CommentReadUseCase commentReadUseCase;

    @GetMapping()
    public CommentListResponseDto commentListResponseDto(
            @PathVariable UUID episodeId,
            @PageableDefault(size=10, sort="createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ){
        pageable = pageable.previousOrFirst();
        return commentReadUseCase.findAllByEpiId(episodeId, pageable);
    }
}