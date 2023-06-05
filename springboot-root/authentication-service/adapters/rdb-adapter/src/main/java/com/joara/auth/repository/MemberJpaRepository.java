package com.joara.auth.repository;

import com.joara.auth.entity.MemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, UUID> {
    List<MemberEntity> findAllBy(Pageable pageable);
}
