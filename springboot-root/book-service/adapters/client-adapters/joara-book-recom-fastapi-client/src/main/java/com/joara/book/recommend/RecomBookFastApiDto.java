package com.joara.book.recommend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

public record RecomBookFastApiDto() {
    @Builder
    public record RecomBooksQueryRequestDto(Long movieId) {}
    public record RecomBooksQueryResponseDto(
            @JsonProperty("predict_result") List<Long> predictResult
    ) {}
}
