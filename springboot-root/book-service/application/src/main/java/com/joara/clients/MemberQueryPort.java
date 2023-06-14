package com.joara.clients;

import com.joara.member.MemberReadModels.MemberIdReadModel;

import java.util.Optional;

public interface MemberQueryPort {
    Optional<MemberIdReadModel> findIdByEmail(String email);
}
