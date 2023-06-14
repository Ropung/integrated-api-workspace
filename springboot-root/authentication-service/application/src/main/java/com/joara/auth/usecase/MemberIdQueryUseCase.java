package com.joara.auth.usecase;

import com.joara.member.MemberReadModels.MemberIdReadModel;

import java.util.Optional;

public interface MemberIdQueryUseCase {
    Optional<MemberIdReadModel> findMemberIdByEmail(String email);
}
