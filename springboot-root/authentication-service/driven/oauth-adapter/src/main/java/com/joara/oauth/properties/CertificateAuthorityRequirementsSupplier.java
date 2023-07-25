package com.joara.oauth.properties;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joara.auth.domain.model.type.CertType;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.Map;

// TODO rename
@Component
public final class CertificateAuthorityRequirementsSupplier {
    private static final Map<CertType, CertificateAuthorityRequirements> map =
            Map.of(
                    CertType.KAKAO,
                    CertificateAuthorityRequirements.builder()
                            // TODO refactor: 환경변수 사용은 모두 application.yml 거치도록(각종 include 된 YAML, Properties 파일)
                            //  또는 환경변수 제공자를 하나로 몰아 놓거나, ... 추적할 수 있게만 확장.
                            .clientId(System.getenv("kakao.client_id"))
                            .grantType("authorization_code")
                            .build()
            );

    public CertificateAuthorityRequirements getRequirements(CertType certBy) {
        return map.get(certBy);
    }

    @Builder
    public record CertificateAuthorityRequirements(
            @JsonProperty("grant_type")
            @JsonInclude(Include.NON_EMPTY)
            String grantType,
            @JsonProperty("client_id")
            String clientId
    ) {}
}
