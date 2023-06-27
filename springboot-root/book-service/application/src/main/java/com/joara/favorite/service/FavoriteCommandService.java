package com.joara.favorite.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.clients.MemberQueryPort;
import com.joara.favorite.respository.FavoriteCommandRepository;
import com.joara.favorite.usecase.FavoriteCreateUseCase;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateResponseDto;
import com.joara.favorite.usecase.mapper.FavoriteBookDtoMapper;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.util.time.ServerTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteCommandService implements FavoriteCreateUseCase {
    private final FavoriteCommandRepository favoriteCommandRepository;
    private final BookQueryRepository bookQueryRepository;
    private final FavoriteBookDtoMapper mapper;
    private final JwtParser jwtParser;
    private final MemberQueryPort memberQueryPort;


    @Override
    public FavoriteCreateResponseDto create(FavoriteCreateRequestDto dto, HttpServletRequest request) {
        OffsetDateTime now = ServerTime.now();
        MemberFavorBook memberFavorBook = mapper.from(dto,now);
        //todo mapper에서 dto로 받은 bookId를 인식하지 못함!
        System.out.println("DDBBUUGG");
        System.out.println(memberFavorBook.BookId);
        return FavoriteCreateResponseDto.builder()
                .success(create(memberFavorBook, request))
                .build();
    }
    @Override
    public boolean create(MemberFavorBook memberFavorBook, HttpServletRequest request){
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();
        String nickname = parser.claims()
                .get("nickname", String.class);

        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();

        Book book = bookQueryRepository.findById(memberFavorBook.BookId)
                .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

        memberFavorBook.genreIdList = book.genreIdList;
        memberFavorBook.bookTitle = book.title;
        memberFavorBook.memberId = memberId;
        memberFavorBook.nickname = nickname;

        favoriteCommandRepository.save(memberFavorBook);
        return true;
    }
}
