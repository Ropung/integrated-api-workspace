package com.joara.auth.service;

import com.joara.auth.repository.MemberQueryRepository;
import com.joara.auth.usecase.MemberIdQueryUseCase;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public final class MemberQueryService implements MemberIdQueryUseCase {
    private final MemberQueryRepository memberQueryRepository;

    @Override
    public Optional<MemberIdReadModel> findMemberIdByEmail(String email) {
        return memberQueryRepository.findIdByEmail(email);
    }
}
