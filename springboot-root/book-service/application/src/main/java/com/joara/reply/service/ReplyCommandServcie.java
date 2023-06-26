package com.joara.reply.service;

import com.joara.book.domain.model.reply.Reply;
import com.joara.book.domain.model.reply.type.ReplyStatus;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.clients.MemberQueryPort;
import com.joara.episode.repository.EpisodeQueryRepository;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.reply.repository.ReplyCommandRepository;
import com.joara.reply.usecase.ReplyCreateUsecase;
import com.joara.reply.usecase.ReplyDeleteUsecase;
import com.joara.reply.usecase.ReplyUpdateUsecase;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateRequestDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyCreateResponseDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyDeleteResponseDto;
import com.joara.reply.usecase.dto.ReplyCommandDto.ReplyUpdateResponseDto;
import com.joara.reply.usecase.mapper.ReplyDtoMapper;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReplyCommandServcie implements ReplyCreateUsecase, ReplyUpdateUsecase, ReplyDeleteUsecase {

    private final ReplyCommandRepository replyCommandRepository;
    private final BookQueryRepository bookQueryRepository;
    private final EpisodeQueryRepository episodeQueryRepository;
    private final ReplyDtoMapper mapper;
    private final MemberQueryPort memberQueryPort;
    private final JwtParser jwtParser;

    @Override
    public ReplyCreateResponseDto create(Long bid, UUID eid, UUID cid, ReplyCreateRequestDto dto, HttpServletRequest request) {
        Reply reply = mapper.from(dto);

        return ReplyCreateResponseDto.builder()
                .success(create(bid, eid, cid, reply, request))
                .build();
    }

    @Override
    public boolean create(Long bid, UUID eid, UUID cid, Reply reply, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isBook = bookQueryRepository.existsById(bid);
        if(!isBook) throw BookErrorCode.BOOK_NOT_FOUND.defaultException();

        boolean isEpisode = episodeQueryRepository.existsById(eid);
        if(!isEpisode) throw BookErrorCode.EPISODE_NOT_FOUND.defaultException();

        // comment가 아직 안만들어짐
//        boolean isComment = commpentQueryRespositoty.existById(cid);
//        if(!isEpisode) throw BookErrorCode.COMMENT_NOT_FOUND.defaultException();

        reply.commentId = cid;
        reply.memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();
        reply.nickname = nickname;
        reply.status = ReplyStatus.ACTIVE;
        reply.createdAt = ServerTime.now();
        replyCommandRepository.save(reply);
        return true;
    }

    @Override
    public ReplyUpdateResponseDto update(Long bid, UUID eid, UUID cid, UUID rid, ReplyCreateRequestDto dto, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isNickname = replyCommandRepository.existsNicknameByNickname(nickname);
        if(!isNickname) throw BookErrorCode.FORBIDDEN.defaultException();

        replyCommandRepository.update(cid, rid, dto.content());

        return ReplyUpdateResponseDto.builder()
                .success(true)
                .build();
    }

    @Override
    public ReplyDeleteResponseDto delete(Long bid, UUID eid, UUID cid, UUID rid, ReplyCreateRequestDto dto, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String nickname = parser.claims()
                .get("nickname", String.class);

        boolean isNickname = replyCommandRepository.existsNicknameByNickname(nickname);
        if(!isNickname) throw BookErrorCode.FORBIDDEN.defaultException();

        replyCommandRepository.delete(rid);

        return ReplyDeleteResponseDto.builder()
                .success(true)
                .build();
    }
}
