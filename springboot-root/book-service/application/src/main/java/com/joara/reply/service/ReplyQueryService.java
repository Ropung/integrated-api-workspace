package com.joara.reply.service;

import com.joara.book.domain.model.reply.Reply;
import com.joara.book.exception.BookErrorCode;
import com.joara.reply.exception.ReplyErrorCode;
import com.joara.reply.repository.ReplyQueryRepository;
import com.joara.reply.usecase.ReplyReadUseCase;
import com.joara.reply.usecase.dto.ReplyQueryDto.ReplyListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyQueryService implements ReplyReadUseCase {
    private final ReplyQueryRepository replyQueryRepository;

    @Override
    public ReplyListResponseDto findAllByCommentId(UUID cid, Pageable pageable) {

        Page<Reply> replySearchResult = replyQueryRepository.findAllByCommentId(cid, pageable);

        long lastPageNumber = replySearchResult.getTotalPages();
        if (pageable.getPageNumber() >= lastPageNumber) {
            throw BookErrorCode.PAGE_OUT_OF_RANGE.defaultException();
        }

        List<Reply> replyList = replySearchResult.getContent();

        if (replySearchResult.isEmpty()) {
            throw ReplyErrorCode.REPLY_NOT_FOUND.defaultException();
        }

        return ReplyListResponseDto.builder()
                .replyList(replyList)
                .lastPage(lastPageNumber)
                .build();
    }

    @Override
    public boolean existsByCommentId(UUID commentId) {
        return replyQueryRepository.existsByCommentId(commentId);
    }
}