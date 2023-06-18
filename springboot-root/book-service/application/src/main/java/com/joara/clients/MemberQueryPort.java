package com.joara.clients;

import com.joara.member.MemberReadModels.MemberIdReadModel;
import com.joara.member.MemberReadModels.MemberProfileReadModel;

import java.util.Optional;

public interface MemberQueryPort {
    Optional<MemberIdReadModel> findIdByEmail(String email);

    Optional<MemberProfileReadModel> findProfileByEmail(String email, String accessToken);

    Optional<MemberProfileReadModel> findProfileByEmail(String email);
}
