package com.joara.auth.repository;

import com.joara.auth.domain.model.Member;
import com.joara.auth.repository.projection.MemberQueryProjection.MemberEmailNicknameProjection;
import com.joara.member.MemberReadModels.DefaultMemberReadModel;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import com.joara.support.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MemberQueryRepository extends BaseRepository<Member, UUID>  {
    Optional<Member> findByEmail(String email);
    Optional<DefaultMemberReadModel> findProjectionByEmail(String email);
    Optional<MemberIdReadModel> findIdByEmail(String email);
    Page<Member> findAll(Pageable pageable);
    // Select 2번 = 데이터(.044) + 카운트를 매번 함(.071) -> (.12)
    // List<Member> findAll(Pageable pageable);  // Select 1번 = 데이터(.044)
    MemberEmailNicknameProjection findEmailAndNicknameByEmail(String email);
    boolean existsMemberByEmail(String email);
}
