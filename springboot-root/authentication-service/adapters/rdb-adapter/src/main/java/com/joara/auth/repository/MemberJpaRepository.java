package com.joara.auth.repository;

import com.joara.auth.entity.MemberEntity;
import com.joara.auth.repository.projection.MemberProjections.DefaultMemberProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
	Optional<MemberEntity> findByEmail(String email);
	Optional<DefaultMemberProjection> findProjectionByEmail(String email);
	List<MemberEntity> findAllBy(Pageable pageable);
}
