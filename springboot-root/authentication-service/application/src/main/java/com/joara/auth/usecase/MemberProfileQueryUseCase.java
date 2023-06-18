package com.joara.auth.usecase;

import com.joara.auth.usecase.dto.MemberQueryDto.MemberProfileQueryResponseDto;
import com.joara.member.MemberReadModels.MemberProfileReadModel;

import java.util.Optional;

public interface MemberProfileQueryUseCase {
    MemberProfileQueryResponseDto findProfileByEmail(String email);
    Optional<MemberProfileReadModel> findProfileByEmailAsOptional(String email);
}
