package com.joara.auth.repository;

import com.joara.auth.domain.model.Member;
import com.joara.auth.entity.MemberEntity;
import com.joara.auth.mapper.MemberEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberPersistence implements MemberRepository {
    // Delegation
     private final MemberJpaRepository memberJpaRepository;
     private final MemberEntityMapper mapper;

    @Override
    public Member save(Member domain) {
        // use mapper: Member -> MemberEntity
        // use mapper: MemberEntity -> Member
        MemberEntity entity = mapper.toEntity(domain);
        MemberEntity savedEntity = memberJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Member> findById(UUID uuid) {
        return memberJpaRepository.findById(uuid)
                .map(mapper::toDomain);
    }

    @Override
    public Page<Member> findAll(Pageable pageable) {
//        return memberJpaRepository.findAll(pageable) // returns Page<MemberEntity> ... -> Low Performance
//                .map(mapper::toDomain);
        return new PageImpl<>(
                memberJpaRepository.findAllBy(pageable)
                        .stream()
                        .map(mapper::toDomain)
                        .toList(),
                pageable,
                memberJpaRepository.count() // TODO use after refactor, ... if necessary
        );
    }
}
