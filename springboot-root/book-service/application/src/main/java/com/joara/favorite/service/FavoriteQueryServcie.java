package com.joara.favorite.service;

import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.book.exception.BookErrorCode;
import com.joara.clients.MemberQueryPort;
import com.joara.exception.status2xx.NoContentException;
import com.joara.favorite.respository.FavoriteQueryRepository;
import com.joara.favorite.usecase.FavoriteReadUseCase;
import com.joara.favorite.usecase.dto.FavoriteQueryDto.FavoriteListResponseDto;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteQueryServcie implements FavoriteReadUseCase {

    private final FavoriteQueryRepository favoriteQueryRepository;
    private final MemberQueryPort memberQueryPort;
    private final JwtParser jwtParser;

    @Override
    public FavoriteListResponseDto findbyMemberId(HttpServletRequest request, Pageable pageable) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        String nickname = parser.claims()
                .get("nickname", String.class);
        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();

        Page<MemberFavorBook> favorSearchResult = favoriteQueryRepository.findByMemberId(memberId, pageable);
        if (favorSearchResult.isEmpty()) {
            throw new NoContentException();
        }

        long lastPageNumber = favorSearchResult.getTotalPages();

        List<MemberFavorBook> memberFavorBookList = favorSearchResult.getContent();

        return FavoriteListResponseDto.builder()
                .memberFavorBookList(memberFavorBookList)
                .lastPage(lastPageNumber)
                .build();
    }
}

