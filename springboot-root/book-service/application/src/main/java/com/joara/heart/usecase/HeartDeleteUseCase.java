package com.joara.heart.usecase;

import com.joara.heart.usecase.dto.HeartCommandDto.HeartRemoveResponseDto;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public interface HeartDeleteUseCase {

    HeartRemoveResponseDto delete(Long bookId, UUID epiId, HttpServletRequest request);
}
