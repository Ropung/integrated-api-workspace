package com.joara.oauth.service;

import com.joara.auth.clients.dto.OauthClientDto.OauthRequestClientDto;
import com.joara.auth.clients.dto.OauthClientDto.OauthResponse;
import com.joara.auth.clients.dto.OauthClientDto.OpenIdPayload;
import com.joara.auth.domain.model.type.CertType;
import com.joara.auth.exception.OauthErrorCode;
import com.joara.jwt.util.Claims;
import com.joara.jwt.util.JwtParser;
import com.joara.jwt.util.JwtParser.JwtPayloadParser;
import com.joara.oauth.properties.CertificateAuthorityRequirementsSupplier;
import com.joara.oauth.properties.CertificateAuthorityRequirementsSupplier.CertificateAuthorityRequirements;
import com.joara.oauth.service.dto.KakaoOauthClientDto.KakaoOauthResponseClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public final class KakaoOauthService implements OauthService {
    private static final String KAKAO_OAUTH_TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    private final CertificateAuthorityRequirementsSupplier requirementsSupplier;
    private final RestTemplate restTemplate;
    private final JwtParser jwtParser;

    @Override
    public OauthResponse authenticate(OauthRequestClientDto requestData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> requestBody = getLinkedMultiValueMap(requestData);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<KakaoOauthResponseClientDto> responseEntity = restTemplate
                .postForEntity(
                        KAKAO_OAUTH_TOKEN_URL,
                        request,
                        KakaoOauthResponseClientDto.class
                );

        if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
            // TODO 상황별로 구분
            throw OauthErrorCode.INVALID_EXTERNAL_TOKEN.defaultException();
        }

        KakaoOauthResponseClientDto kakaoResponse = responseEntity.getBody();

        JwtPayloadParser payloadParser = jwtParser
                .withAccessToken(kakaoResponse.openIdToken());

        Claims claims = payloadParser.claims();

        Long epoch = claims.get("auth_time", Long.class);
        OffsetDateTime authTime = OffsetDateTime.ofInstant(
                Instant.ofEpochSecond(epoch), ZoneId.of("Asia/Seoul")
        );

        // TODO refactor: map claims to openIdPayload
        OpenIdPayload openIdPayload = OpenIdPayload.builder()
                .openId(Long.parseLong(payloadParser.subject()))
                .email(claims.get("email", String.class))
                .nickname(claims.get("nickname", String.class))
                .authTime(authTime)
                .issuer(claims.get("iss", String.class))
                .picture(claims.get("picture", String.class))
                .build();

        return OauthResponse.builder()
                .openIdPayload(openIdPayload)
                .tokenType(kakaoResponse.tokenType()) // "Bearer"
                .oauthAccessToken(kakaoResponse.oauthAccessToken())
                .oauthRefreshToken(kakaoResponse.oauthRefreshToken())
                .expiresIn(kakaoResponse.expiresIn())
                .refreshTokenExpiresIn(kakaoResponse.refreshTokenExpiresIn())
                .scope(kakaoResponse.scope())
                .build();
    }

    private LinkedMultiValueMap<String, String> getLinkedMultiValueMap(OauthRequestClientDto requestData) {
        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        CertificateAuthorityRequirements requirements = requirementsSupplier.getRequirements(CertType.KAKAO);

        map.add("grant_type", requirements.grantType());
        map.add("client_id", requirements.clientId());
        map.add("redirect_uri", requestData.redirectUri());
        map.add("code", requestData.code());

        if (requestData.clientSecret() != null && !"".equals(requestData.clientSecret())) {
            map.add("client_secret", requestData.clientSecret());
        }

        return map;
    }
}
