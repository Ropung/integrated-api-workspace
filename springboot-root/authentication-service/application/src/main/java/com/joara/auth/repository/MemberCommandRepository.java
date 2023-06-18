package com.joara.auth.repository;

import com.joara.auth.domain.model.Member;
import com.joara.support.repository.BaseCommandRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface MemberCommandRepository extends BaseCommandRepository<Member, UUID> {
	Page<Member> findAll(Pageable pageable);
	boolean existsMemberByEmail(String email);
}
