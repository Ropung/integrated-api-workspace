package com.joara.web.comment;

import com.joara.comment.usecase.CommentCreateUsecase;
import com.joara.comment.usecase.CommentDeleteUsecase;
import com.joara.comment.usecase.CommentUpdateUsecase;
import com.joara.comment.usecase.dto.CommentCommandDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateResponseDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentDeleteResponseDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bookId}/episode/{episodeId}/comment")
public class CommentCommandApi {

    private final CommentCreateUsecase commentCreateUsecase;
    private final CommentUpdateUsecase commentUpdateUsecase;
    private final CommentDeleteUsecase commentDeleteUsecase;

    @PostMapping()
    public CommentCreateResponseDto create(
            @PathVariable Long bookId, @PathVariable UUID episodeId,
            @RequestBody @Valid CommentCommandDto.CommentCreateRequestDto dto,
            HttpServletRequest request
    ){
        return commentCreateUsecase.create(bookId, episodeId, dto, request);
    }

    @PutMapping("/{commentId}")
    public CommentUpdateResponseDto update(
            @PathVariable Long bookId, @PathVariable UUID episodeId, @PathVariable UUID commentId,
            @RequestBody @Valid CommentCommandDto.CommentCreateRequestDto dto,
            HttpServletRequest request
    ){

        return commentUpdateUsecase.update(bookId, episodeId, commentId, dto, request);
    }

    @DeleteMapping("/{commentId}")
    public CommentDeleteResponseDto delete(
            @PathVariable Long bookId, @PathVariable UUID episodeId, @PathVariable UUID commentId,
            HttpServletRequest request
    ){

        return commentDeleteUsecase.delete(bookId, episodeId, commentId, request);
    }
}
