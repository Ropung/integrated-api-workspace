package com.joara.comment.service;

import com.joara.book.domain.model.comment.Comment;
import com.joara.book.domain.model.comment.type.CommentStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.clients.MemberQueryPort;
import com.joara.comment.repository.CommentCommandRepository;
import com.joara.comment.usecase.CommentCreateUsecase;
import com.joara.comment.usecase.CommentDeleteUsecase;
import com.joara.comment.usecase.CommentUpdateUsecase;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateRequestDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentCreateResponseDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentDeleteResponseDto;
import com.joara.comment.usecase.dto.CommentCommandDto.CommentUpdateResponseDto;
import com.joara.comment.usecase.mapper.CommentDtoMapper;
import com.joara.episode.repository.EpisodeQueryRepository;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateRequestDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyDeleteResponseDto;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentCommandServcie implements CommentCreateUsecase, CommentUpdateUsecase, CommentDeleteUsecase {

    private final CommentCommandRepository commentCommandRepository;
    private final BookQueryRepository bookQueryRepository;
    private final EpisodeQueryRepository episodeQueryRepository;
    private final CommentDtoMapper mapper;
    private final MemberQueryPort memberQueryPort;
    private final JwtParser jwtParser;

    @Override
    public CommentCreateResponseDto create(Long bookId, UUID episodeId, CommentCreateRequestDto dto, HttpServletRequest request) {
        Comment comment = mapper.from(dto);

        return CommentCreateResponseDto.builder()
                .success(create(bookId, episodeId, comment, request))
                .build();
    }

    @Override
    public boolean create(Long bookId, UUID episodeId, Comment comment, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isBook = bookQueryRepository.existsById(bookId);
        if(!isBook) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

        boolean isEpisode = episodeQueryRepository.existsById(episodeId);
        if(!isEpisode) throw BookErrorCode.EPISODE_NOT_FOUND.defaultException();

        comment.epiId = episodeId;
        comment.memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();
        comment.nickname = nickname;
        comment.status = CommentStatus.ACTIVE;
        comment.createdAt = ServerTime.now();
        commentCommandRepository.save(comment);
        return true;
    }

    @Override
    public CommentUpdateResponseDto update(Long bookId, UUID episodeId, UUID commentId, CommentCreateRequestDto dto, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isNickname = commentCommandRepository.existsNicknameByNickname(nickname);
        if(!isNickname) throw BookErrorCode.FORBIDDEN.defaultException();

        commentCommandRepository.update(episodeId, commentId, dto.content());

        return CommentUpdateResponseDto.builder()
                .success(true)
                .build();
    }

    @Override
    public CommentDeleteResponseDto delete(Long bookId, UUID episodeId, UUID commentId, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isNickname = commentCommandRepository.existsNicknameByNickname(nickname);
        if(!isNickname) throw BookErrorCode.FORBIDDEN.defaultException();

        commentCommandRepository.delete(commentId);

        return CommentDeleteResponseDto.builder()
                .success(true)
                .build();
    }
}
