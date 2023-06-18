package com.joara.auth.repository;

import com.joara.auth.domain.model.Member;
import com.joara.auth.entity.MemberEntity;
import com.joara.auth.mapper.MemberEntityMapper;
import com.joara.auth.repository.projection.MemberProjections.DefaultMemberProjection;
import com.joara.auth.repository.projection.MemberQueryProjection.MemberEmailNicknameProjection;
import com.joara.auth.repository.projection.MemberQueryProjection.MemberIdProjection;
import com.joara.member.MemberReadModels.DefaultMemberReadModel;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberPersistence implements MemberCommandRepository, MemberQueryRepository {
    // Delegation(위임)
    private final MemberJpaRepository memberJpaRepository;
    private final MemberEntityMapper mapper;

    /*
    mapper(DOMAIN -> ENTITY)
    -> jpaRepository.do()
    mapper(ENTITY -> DOMAIN)
     */

    @Override
    public Member save(Member domain) {
        // DOMAIN -> ENTITY -> save -> ENTITY -> DOMAIN
        MemberEntity entity = mapper.toEntity(domain);
        MemberEntity savedEntity = memberJpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }
    
    @Override
    public boolean existsMemberByEmail(String email) {
        return memberJpaRepository.existsMemberByEmail(email);
    }
    
    @Override
    public MemberEmailNicknameProjection findEmailAndNicknameByEmail(String email) {
        return null;
    }
    
    @Override
    public Optional<Member> findByEmail(String email) {
        Optional<MemberEntity> optionalMember = memberJpaRepository.findByEmail(email);
        // Optional<MemberEntity> -> Optional<Member>
        return optionalMember.map(mapper::toDomain);
    }
    
    @Override
    public Optional<DefaultMemberReadModel> findProjectionByEmail(String email) {
        Optional<DefaultMemberProjection> member =
                memberJpaRepository.findProjectionByEmail(email);
        
        return member.map(mapper::toReadModel);
    }
    
    @Override
    public Optional<MemberIdReadModel> findIdByEmail(String email) {
        Optional<MemberIdProjection> memberIdWrapper =
                memberJpaRepository.findIdByEmail(email);
        return memberIdWrapper.map(mapper::toReadModel);
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
