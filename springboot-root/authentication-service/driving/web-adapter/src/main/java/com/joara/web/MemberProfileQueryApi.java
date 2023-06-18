package com.joara.web;

import com.joara.auth.usecase.MemberProfileQueryUseCase;
import com.joara.auth.usecase.dto.MemberQueryDto.MemberProfileQueryResponseDto;
import com.joara.jwt.util.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public final class MemberProfileQueryApi {
    private final MemberProfileQueryUseCase memberQueryUseCase;
    private final JwtParser jwtParser;

    @GetMapping("/me")
    public MemberProfileQueryResponseDto findProfileByEmail(HttpServletRequest request) {
        String email = jwtParser.withRequest(request)
                .claims()
                .getSubject();

        return memberQueryUseCase.findProfileByEmail(email);
    }
}
