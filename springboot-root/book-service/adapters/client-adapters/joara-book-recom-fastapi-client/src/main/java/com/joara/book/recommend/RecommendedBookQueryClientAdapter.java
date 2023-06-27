package com.joara.book.recommend;

import com.joara.book.recommend.RecomBookFastApiDto.RecomBooksQueryRequestDto;
import com.joara.book.recommend.RecomBookFastApiDto.RecomBooksQueryResponseDto;
import com.joara.clients.RecommendedBookQueryPort;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendedBookQueryClientAdapter implements RecommendedBookQueryPort {
    // AJAX 비슷한.
    private final RestTemplate restTemplate;

    private static final class TempServiceDiscoveryResults {
        static final String RECOM_BOOK_QUERY_SERVICE = "http://127.0.0.1:8000";
    }

    @Override
    public List<Long> findRecommendedBookIdsByBookId(Long bookId) {
        String target = TempServiceDiscoveryResults.RECOM_BOOK_QUERY_SERVICE;
        String path = "/items";

        ResponseEntity<RecomBooksQueryResponseDto> recomBooksIdResponse =
                restTemplate.postForEntity(
                        "%s%s".formatted(target, path), // 받아 올 URL
                        RecomBooksQueryRequestDto.builder().movieId(bookId).build(),
                        RecomBooksQueryResponseDto.class // 받아 올 타입
                );

        if (!recomBooksIdResponse.getStatusCode().is2xxSuccessful()) {
            throw new IllegalStateException("...");
        }

        if (recomBooksIdResponse.getBody() == null) {
            return List.of();
        }

        return recomBooksIdResponse
                .getBody()
                .predictResult();
    }
}
