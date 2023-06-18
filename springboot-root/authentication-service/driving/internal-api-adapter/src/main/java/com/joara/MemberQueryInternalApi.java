package com.joara;

import com.joara.auth.exception.MemberQueryErrorCode;
import com.joara.auth.usecase.MemberIdQueryUseCase;
import com.joara.auth.usecase.MemberProfileQueryUseCase;
import com.joara.member.MemberReadModels.MemberIdReadModel;
import com.joara.member.MemberReadModels.MemberProfileReadModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public final class MemberQueryInternalApi {
    private final MemberIdQueryUseCase memberIdQueryUseCase;
    private final MemberProfileQueryUseCase memberProfileQueryUseCase;

    // Internal -> HTTP/HTTPS not required
    // Internal -> (not necessary DTO but) READ MODEL
    @GetMapping("/{email}/id")
    public MemberIdReadModel id(@PathVariable String email) {
        return memberIdQueryUseCase.findMemberIdByEmail(email)
                .orElseThrow(MemberQueryErrorCode.NOT_FOUND::defaultException);
    }

    @GetMapping("/{email}/profile")
    public MemberProfileReadModel profile(@PathVariable String email) {
        return memberProfileQueryUseCase.findProfileByEmailAsOptional(email)
                .orElseThrow(MemberQueryErrorCode.NOT_FOUND::defaultException);
    }
}
