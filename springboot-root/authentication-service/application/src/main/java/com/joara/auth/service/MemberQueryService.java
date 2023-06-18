package com.joara.auth.service;

import com.joara.auth.exception.MemberQueryErrorCode;
import com.joara.auth.repository.MemberQueryRepository;
import com.joara.auth.usecase.MemberIdQueryUseCase;
import com.joara.auth.usecase.MemberProfileQueryUseCase;
import com.joara.auth.usecase.dto.MemberQueryDto.MemberProfileQueryResponseDto;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import com.joara.member.MemberReadModels.MemberProfileReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MemberQueryService implements MemberIdQueryUseCase, MemberProfileQueryUseCase {
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Optional<MemberIdReadModel> findMemberIdByEmail(String email) {
        return memberQueryRepository.findIdByEmail(email);
    }

    @Override
    public MemberProfileQueryResponseDto findProfileByEmail(String email) {
        // 있으면 꺼내고, 없으면 예외를 던짐.
        MemberProfileReadModel profile = memberQueryRepository
                .findProfileByEmail(email) // Optional<...>
                .orElseThrow(MemberQueryErrorCode.NOT_FOUND::defaultException);

        return MemberProfileQueryResponseDto.builder()
                .profile(profile)
                .build();
    }

    @Override
    public Optional<MemberProfileReadModel> findProfileByEmailAsOptional(String email) {
        return memberQueryRepository.findProfileByEmail(email);
    }
}
