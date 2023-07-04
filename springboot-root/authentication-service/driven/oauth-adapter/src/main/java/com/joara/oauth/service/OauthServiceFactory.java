package com.joara.oauth.service;

import com.joara.auth.domain.model.type.CertType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class OauthServiceFactory {
    private final KakaoOauthService kakaoOauthService;

    public OauthService create(CertType certBy) {
        return switch (certBy) {
            case KAKAO -> kakaoOauthService;
            // TODO google oauth service (at factory)
            case GOOGLE -> null;
            case SELF -> {
                assert false : "";
                throw new Error("");
            }
        };
    }
}
