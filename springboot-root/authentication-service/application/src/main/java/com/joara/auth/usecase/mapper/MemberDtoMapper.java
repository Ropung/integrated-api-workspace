package com.joara.auth.usecase.mapper;

import com.joara.auth.domain.model.Member;
import com.joara.auth.domain.model.type.AccountStatus;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.usecase.dto.MemberSignUpDto.MemberSignUpRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.OffsetDateTime;

@Mapper(componentModel = "spring")
public interface MemberDtoMapper {
	@Mapping(target = "password", source = "dto.rawPassword")
	Member from(MemberSignUpRequestDto dto, AccountStatus status, OffsetDateTime createdAt, CertType certificatedBy);
}
