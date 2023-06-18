package com.joara.auth.usecase.dto;

import com.joara.member.MemberReadModels.MemberProfileReadModel;
import lombok.Builder;

public record MemberQueryDto() {
    @Builder
    public record MemberProfileQueryResponseDto(
            MemberProfileReadModel profile
    ) {}
}
