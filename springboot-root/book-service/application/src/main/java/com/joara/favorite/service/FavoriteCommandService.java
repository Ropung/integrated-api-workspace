package com.joara.favorite.service;

import com.joara.book.domain.model.book.Book;
import com.joara.book.domain.model.book.MemberFavorBook;
import com.joara.book.exception.BookErrorCode;
import com.joara.book.repository.BookQueryRepository;
import com.joara.clients.MemberQueryPort;
import com.joara.favorite.respository.FavoriteCommandRepository;
import com.joara.favorite.respository.FavoriteQueryRepository;
import com.joara.favorite.usecase.FavoriteCreateUseCase;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteCreateResponseDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteRequestDto;
import com.joara.favorite.usecase.dto.FavoriteCommandDto.FavoriteDeleteResponseDto;
import com.joara.favorite.usecase.mapper.FavoriteBookDtoMapper;
import com.joara.favorite.usecase.mapper.FavoriteDeleteUseCase;
import com.joara.genre.service.GenreQueryService;
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
public class FavoriteCommandService implements FavoriteCreateUseCase , FavoriteDeleteUseCase {
    private final FavoriteCommandRepository favoriteCommandRepository;
    private final FavoriteQueryRepository favoriteQueryRepository;
    private final BookQueryRepository bookQueryRepository;
    private final FavoriteBookDtoMapper mapper;
    private final JwtParser jwtParser;
    private final MemberQueryPort memberQueryPort;
    private final GenreQueryService genreQueryService;

    @Override
    public FavoriteCreateResponseDto create(FavoriteCreateRequestDto dto, HttpServletRequest request) {
        MemberFavorBook memberFavorBook = mapper.from(dto);

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
        boolean isFavorite = favoriteCommandRepository.existsByMemberIdAndBookId(memberId,memberFavorBook.bookId);
        if(!isFavorite){
            Book book = bookQueryRepository.findById(memberFavorBook.bookId)
                    .orElseThrow(BookErrorCode.BOOK_NOT_FOUND::defaultException);

            memberFavorBook.genreIdList = book.genreIdList;
            memberFavorBook.bookTitle = book.title;
            memberFavorBook.memberId = memberId;
            memberFavorBook.nickname = book.nickname;
            memberFavorBook.createdAt = ServerTime.now();

            favoriteCommandRepository.save(memberFavorBook);
        }
        return true;
    }

    @Override
    public FavoriteDeleteResponseDto delete(FavoriteDeleteRequestDto dto, HttpServletRequest request) {
        JwtPayloadParser parser = jwtParser.withRequest(request);
        String email = parser.subject();

        UUID memberId = memberQueryPort.findIdByEmail(email)
                .orElseThrow(BookErrorCode.SERVICE_UNAVAILABLE::defaultException)
                .id();

//        UUID favoriteId = favoriteQueryRepository.findByBookIdAndMemberId(dto.bookId(),memberId);
//        System.out.println("DDDBBUG");
//        System.out.println(favoriteId);

        favoriteCommandRepository.deleteByMemberIdAndBookId(memberId,dto.bookId());


        return FavoriteDeleteResponseDto.builder()
                .success(true)
                .build();
    }
}
