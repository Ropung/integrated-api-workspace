package com.joara.auth.mapper;

import com.joara.auth.domain.model.Member;
import com.joara.auth.entity.MemberEntity;
import com.joara.support.mapper.BaseEntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberEntityMapper extends BaseEntityMapper<Member, MemberEntity> {
}
