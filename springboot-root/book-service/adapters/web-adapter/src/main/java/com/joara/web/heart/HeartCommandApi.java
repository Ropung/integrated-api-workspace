package com.joara.web.heart;
import com.joara.heart.usecase.HeartCreateUseCase;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books/{bookId}/episode/{epiId}/heart")
public class HeartCommandApi {
    private final HeartCreateUseCase heartCreateUseCase;
    @PostMapping()
    public HeartCreateResponseDto create(
            @PathVariable Long bookId,
            @PathVariable UUID epiId,
            HttpServletRequest request
            ){
        return heartCreateUseCase.create(bookId,epiId, request);
    }
}
