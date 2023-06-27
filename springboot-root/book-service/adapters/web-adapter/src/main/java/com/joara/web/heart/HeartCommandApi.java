package com.joara.web.heart;

import com.joara.heart.usecase.HeartCreateUseCase;
import com.joara.heart.usecase.HeartDeleteUseCase;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartCreateResponseDto;
import com.joara.heart.usecase.dto.HeartCommandDto.HeartRemoveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final HeartDeleteUseCase heartDeleteUseCase;
    @PostMapping()
    public HeartCreateResponseDto create(
            @PathVariable Long bookId,
            @PathVariable UUID epiId,
            HttpServletRequest request
            ){
        return heartCreateUseCase.create(bookId,epiId, request);
    }
    @DeleteMapping("")
    public HeartRemoveResponseDto delete(
            @PathVariable Long bookId,
            @PathVariable UUID epiId,
            HttpServletRequest request) {

        return heartDeleteUseCase.delete(bookId,epiId,request);
    }
}
