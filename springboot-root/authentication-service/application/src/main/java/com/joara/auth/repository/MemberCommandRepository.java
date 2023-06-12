package com.joara.auth.repository;

import com.joara.auth.domain.model.Member;
import com.joara.support.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface MemberCommandRepository extends BaseRepository<Member, UUID> {
	Page<Member> findAll(Pageable pageable);
	boolean existsMemberByEmail(String email);
}
