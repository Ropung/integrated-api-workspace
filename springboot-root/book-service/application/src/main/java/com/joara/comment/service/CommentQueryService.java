package com.joara.comment.service;

import com.joara.book.domain.model.comment.Comment;
import com.joara.book.exception.BookErrorCode;
import com.joara.comment.exception.CommentErrorCode;
import com.joara.comment.repository.CommentQueryRepository;
import com.joara.comment.usecase.CommentReadUseCase;
import com.joara.comment.usecase.dto.CommentQueryDto.CommentListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentQueryService implements CommentReadUseCase {
    private final CommentQueryRepository commentQueryRepository;

    @Override
    public CommentListResponseDto findAllByEpiId(UUID episodeId, Pageable pageable) {

        Page<Comment> commentSearchResult = commentQueryRepository.findAllByEpiId(episodeId, pageable);

        long lastPageNumber = commentSearchResult.getTotalPages();
        if (pageable.getPageNumber() >= lastPageNumber) {
            throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
        }

        List<Comment> commentList = commentSearchResult.getContent();

        if (commentSearchResult.isEmpty()) {
            throw CommentErrorCode.COMMENT_NOT_FOUND.defaultException();
        }

        return CommentListResponseDto.builder()
                .commentList(commentList)
                .lastPage(lastPageNumber)
                .build();
    }
}