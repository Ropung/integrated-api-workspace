package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.MemberQueryDto.MemberProfileQueryResponseDto;

public interface MemberProfileQueryUseCase {
    MemberProfileQueryResponseDto findProfileByEmail(String email);
}
