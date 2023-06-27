package com.joara.heart.service;

import com.joara.book.domain.model.episode.EpisodeHeart;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.usecase.dto.BookCommandDto.BookRemoveResponseDto;
import com.joara.clients.MemberQueryPort;
import com.joara.heart.repository.HeartCommandRepository;
import com.joara.heart.usecase.HeartCreateUseCase;
import com.joara.heart.usecase.HeartDeleteUseCase;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartCreateResponseDto;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartRemoveResponseDto;
import com.joara.heart.usecase.mapper.HeartDtoMapper;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeartCommandService implements HeartCreateUseCase, HeartDeleteUseCase {
    private final HeartCommandRepository heartCommandRepository;
    private final MemberQueryPort memberQueryPort;
    private final HeartDtoMapper mapper;
    private final JwtParser jwtParser;
    @Override
    public HeartCreateResponseDto create(Long bookId, UUID epiId, HttpServletRequest request) {
        EpisodeHeart episodeHeart = mapper.from(bookId,epiId);
        return HeartCreateResponseDto.builder()
                .success(create(episodeHeart, request))
                .build();
    }
    @Override
    public boolean create(EpisodeHeart episodeHeart, HttpServletRequest request){
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        String nickname = parser.claims()
                .get("nickname", String.class);
        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();
        UUID epiId = episodeHeart.epiId;
        boolean isHeart = heartCommandRepository.existsByMemberIdAndEpiId(memberId,epiId);
        if(!isHeart){
            episodeHeart.memberId = memberId;
            episodeHeart.nickname = nickname;
            heartCommandRepository.save(episodeHeart);
        return true;
        }
        else{
            return false;
        }
    }
    @Override
    public HeartRemoveResponseDto delete(Long bookId, UUID epiId, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();

        heartCommandRepository.deleteByMemberId(memberId);

        return HeartRemoveResponseDto.builder()
                .success(true)
                .build();
    }


}
