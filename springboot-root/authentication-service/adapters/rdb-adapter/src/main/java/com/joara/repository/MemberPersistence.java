package com.joara.repository;

import com.joara.domain.model.Member;
import com.joara.domain.repository.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public final class MemberPersistence implements Members {
    // Delegation
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public Member save(Member domain) {
        // use mapper: Member -> MemberEntity
        // use mapper: MemberEntity -> Member
        return null;
    }

    @Override
    public Optional<Member> findById(UUID uuid) {
        return Optional.empty();
    }
}
