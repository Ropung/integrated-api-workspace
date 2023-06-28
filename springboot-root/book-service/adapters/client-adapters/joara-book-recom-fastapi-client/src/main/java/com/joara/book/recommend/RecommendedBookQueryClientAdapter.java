package com.joara.book.recommend;

import com.joara.book.recommend.dto.RecomBookFastApiDto.RecomBooksQueryRequestDto;
import com.joara.book.recommend.dto.RecomBookFastApiDto.RecomBooksQueryResponseDto;
import com.joara.clients.RecommendedBookQueryPort;
import com.joara.exception.status2xx.NoContentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
            throw new NoContentException();
        }

        return recomBooksIdResponse
                .getBody()
                .predictResult();
    }
}
